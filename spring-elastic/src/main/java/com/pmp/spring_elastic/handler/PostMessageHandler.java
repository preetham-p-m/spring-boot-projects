package com.pmp.spring_elastic.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmp.spring_elastic.model.Post;
import com.pmp.spring_elastic.repository.elastic.PostElasticRepository;

@Component
public class PostMessageHandler {

    @Autowired
    private PostElasticRepository postElasticRepository;

    @RabbitListener(queues = "post")
    public void handlePostCreateMessage(String post) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Post postObj = objectMapper.readValue(post, Post.class);
        postElasticRepository.save(postObj);
    }

}
