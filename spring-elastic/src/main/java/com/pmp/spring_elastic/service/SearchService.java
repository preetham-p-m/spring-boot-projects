package com.pmp.spring_elastic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmp.spring_elastic.repository.elastic.SearchIndex;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;

@Service
public class SearchService {
    private static final String ElasticRepository = "ElasticRepository";

    @Autowired
    private Map<String, SearchIndex> searchIndexes;

    public List<Object> getFromSearchIndex(String index, Object query) {
        if (!searchIndexes.containsKey(index + ElasticRepository)) {
            throw new IllegalArgumentException(String.format("Invalid Search Index name %s", index));
        }

        // TODO: Need to implement query and result pattern
        return new ArrayList<>();
    }
}
