package com.pmp.spring_elastic.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;

public class ElasticSearchHelper {

    public static Map<String, Object> extractElasticResultFromResponse(SearchResponse<JsonData> elasticResponse) {
        List<Hit<JsonData>> hits = elasticResponse.hits().hits();
        List<Map<String, Object>> returnValue = new ArrayList<>();

        for (Hit<JsonData> hit : hits) {
            Map<String, Object> item = new HashMap<>();
            item.put("_id", hit.id());
            item.put("_index", hit.index());
            item.put("_score", hit.score());
            item.put("_source", hit.source().to(Map.class)); // Actual document content

            returnValue.add(item);
        }

        // Pagination metadata
        long totalHits = elasticResponse.hits().total() != null ? elasticResponse.hits().total().value() : 0;
        int pageSize = hits.size();

        Map<String, Object> result = new HashMap<>();
        result.put("hits", returnValue);
        result.put("pagination", Map.of(
                "total", totalHits,
                "pageSize", pageSize));

        return result;
    }

    public static Map<String, Object> extractElasticError(Exception e) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Failed to execute search: " + e.getMessage());
        return error;
    }

}
