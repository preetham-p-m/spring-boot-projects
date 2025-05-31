package com.pmp.dev_lab.rest.web_client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.pmp.dev_lab.rest.Todo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class WebClientMain {

    private final WebClient webClient;

    public void getTodo(String todoId) {
        List<Todo> todos = webClient.get().uri("https://jsonplaceholder.typicode.com/todos")
                .header(HttpHeaders.AUTHORIZATION, "").retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Todo>>() {
                }).block();

        todos.forEach(System.out::println);
    }
}
