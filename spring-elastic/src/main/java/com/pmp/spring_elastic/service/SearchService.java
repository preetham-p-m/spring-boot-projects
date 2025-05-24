package com.pmp.spring_elastic.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.pmp.spring_elastic.helper.ElasticSearchHelper;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.JsonpMapper;
import jakarta.annotation.PostConstruct;
import jakarta.json.Json;

@Service
public class SearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private static Set<String> searchIndexes;

    @PostConstruct
    private void getSearchIndexes() {
        try {
            searchIndexes = elasticsearchClient.cat().indices()
                    .valueBody()
                    .stream()
                    // Remove Internal Indexes
                    .filter(catIndexRecord -> !catIndexRecord.index().startsWith(".internal"))
                    .map(catIndexRecord -> catIndexRecord.index())
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            searchIndexes = java.util.Collections.emptySet();
        }
    }

    public ResponseEntity<Map<String, Object>> getFromSearchIndex(String index, JsonNode queryDslJson) {
        if (!searchIndexes.contains(index)) {
            throw new IllegalArgumentException(String.format("Invalid Search Index name %s", index));
        }

        // Convert JsonNode to String
        String queryDslString = queryDslJson.toString();

        // Parse the JSON string to a Query using the Elasticsearch client
        JsonpMapper mapper = elasticsearchClient._transport().jsonpMapper();
        Query query = mapper.deserialize(
                Json.createParser(new StringReader(queryDslString)),
                Query.class);

        try {
            SearchResponse<JsonData> response = elasticsearchClient.search(s -> s
                    .index(index)
                    .query(query),
                    JsonData.class);

            var result = ElasticSearchHelper.extractElasticResultFromResponse(response);

            return ResponseEntity.ok(result);
        } catch (ElasticsearchException | IOException e) {
            e.printStackTrace();
            var error = ElasticSearchHelper.extractElasticError(e);
            return ResponseEntity.status(500).body(error);
        }
    }

}
