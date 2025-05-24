package com.pmp.spring_elastic.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmp.spring_elastic.mapper.PostMapper;
import com.pmp.spring_elastic.model.MessageType;
import com.pmp.spring_elastic.model.Post;
import com.pmp.spring_elastic.model.PostMessage;
import com.pmp.spring_elastic.repository.elastic.PostsElasticRepository;

@Component
public class PostMessageHandler {

    @Autowired
    private PostsElasticRepository postElasticRepository;

    @RabbitListener(queues = "post")
    public void handlePostCreateMessage(String postJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PostMessage postMessageObj = objectMapper.readValue(postJson, PostMessage.class);
        Post post = PostMapper.toEntity(postMessageObj);

        switch (postMessageObj.getMessageType()) {
            case MessageType.CREATE,
                    MessageType.UPDATE ->
                saveOrUpdatePost(post);
            case MessageType.DELETE -> deletePost(post);
            default -> throw new Exception("Invalid Post Message Type: " + postMessageObj);
        }
    }

    private void saveOrUpdatePost(Post post) {
        this.postElasticRepository.save(post);
    }

    private void deletePost(Post post) {
        this.postElasticRepository.delete(post);
    }

}
