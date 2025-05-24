package com.pmp.spring_elastic.mapper;

import com.pmp.spring_elastic.model.MessageType;
import com.pmp.spring_elastic.model.Post;
import com.pmp.spring_elastic.model.PostMessage;

public class PostMapper {

    public static PostMessage toMessage(Post post, MessageType messageType) {
        var postMessage = new PostMessage(messageType);
        postMessage.setId(post.getId());
        postMessage.setAuthor(post.getAuthor());
        postMessage.setContent(post.getContent());
        postMessage.setTitle(post.getTitle());

        return postMessage;
    }

    public static Post toEntity(PostMessage postMessage) {
        var post = new Post();
        post.setId(postMessage.getId());
        post.setAuthor(postMessage.getAuthor());
        post.setContent(postMessage.getContent());
        post.setTitle(postMessage.getTitle());

        return post;
    }
}
