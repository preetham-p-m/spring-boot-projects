package com.pmp.spring_elastic.repository.elastic;

import java.util.UUID;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.pmp.spring_elastic.model.Post;

@Repository
public interface PostElasticRepository extends ElasticsearchRepository<Post, UUID>, SearchIndex {

}
