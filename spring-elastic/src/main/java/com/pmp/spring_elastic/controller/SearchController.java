package com.pmp.spring_elastic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmp.spring_elastic.service.SearchService;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("{index}")
    public List<Object> getFromSearchIndex(@PathVariable String index, @RequestBody Object entity) {
        return this.searchService.getFromSearchIndex(index, entity);
    }

}
