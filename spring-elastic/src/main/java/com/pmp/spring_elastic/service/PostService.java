package com.pmp.spring_elastic.service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmp.spring_elastic.configuration.RabbitMQConfiguration;
import com.pmp.spring_elastic.mapper.PostMapper;
import com.pmp.spring_elastic.model.MessageType;
import com.pmp.spring_elastic.model.Post;
import com.pmp.spring_elastic.model.PostMessage;
import com.pmp.spring_elastic.repository.jpa.PostJpaRepository;

@Service
public class PostService {

    @Autowired
    private PostJpaRepository postRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Post savePost(Post post) {
        Post postRecord = postRepository.save(post);
        this.sendMessage(postRecord, MessageType.CREATE);
        return postRecord;
    }

    public Post getPostById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Post %s not found", id)));
    }

    public void deletePostById(UUID id) {
        var post = getPostById(id);
        postRepository.deleteById(post.getId());
        this.sendMessage(post, MessageType.DELETE);
    }

    public Post updatePost(UUID id, Post post) {
        var existingPost = getPostById(id);

        if (post.getTitle() != null) {
            existingPost.setTitle(post.getTitle());
        }

        if (post.getContent() != null) {
            existingPost.setContent(post.getContent());
        }

        var postRecord = postRepository.save(existingPost);
        this.sendMessage(postRecord, MessageType.UPDATE);
        return postRecord;
    }

    private void sendMessage(Post post, MessageType messageType) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            PostMessage postMessage = PostMapper.toMessage(post, messageType);
            String postJson = mapper.writeValueAsString(postMessage);
            Message message = MessageBuilder
                    .withBody(postJson.getBytes(StandardCharsets.UTF_8))
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();

            rabbitTemplate.send(
                    RabbitMQConfiguration.EXCHANGE,
                    RabbitMQConfiguration.ROUTING_KEY,
                    message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
