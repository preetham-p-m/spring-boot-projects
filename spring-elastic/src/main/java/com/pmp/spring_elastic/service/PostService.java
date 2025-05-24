package com.pmp.spring_elastic.service;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        post.setCreatedAt(LocalDateTime.now());

        Post postRecord = postRepository.save(post);
        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.EXCHANGE,
                RabbitMQConfiguration.ROUTING_KEY,
                postRecord);

        return postRecord;
    }

}
