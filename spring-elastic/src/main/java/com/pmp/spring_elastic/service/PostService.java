package com.pmp.spring_elastic.service;

import java.nio.charset.StandardCharsets;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmp.spring_elastic.configuration.RabbitMQConfiguration;
import com.pmp.spring_elastic.model.Post;
import com.pmp.spring_elastic.repository.jpa.PostJpaRepository;

@Service
public class PostService {

    @Autowired
    private PostJpaRepository postRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Post savePost(Post post) {
        // post.setCreatedAt(LocalDateTime.now());

        Post postRecord = postRepository.save(post);

        ObjectMapper mapper = new ObjectMapper();
        String postJson = "";
        try {
            postJson = mapper.writeValueAsString(postRecord);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Message message = MessageBuilder
                .withBody(postJson.getBytes(StandardCharsets.UTF_8))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.send(
                RabbitMQConfiguration.EXCHANGE,
                RabbitMQConfiguration.ROUTING_KEY,
                message);

        return postRecord;
    }

}
