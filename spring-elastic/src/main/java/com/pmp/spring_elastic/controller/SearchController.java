package com.pmp.spring_elastic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.pmp.spring_elastic.service.SearchService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("{index}")
    public ResponseEntity<Map<String, Object>> getFromSearchIndex(@PathVariable String index,
            @RequestBody JsonNode entity) {
        return this.searchService.getFromSearchIndex(index, entity);
    }

}
