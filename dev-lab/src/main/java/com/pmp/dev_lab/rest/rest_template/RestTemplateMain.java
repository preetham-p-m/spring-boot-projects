package com.pmp.dev_lab.rest.rest_template;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pmp.dev_lab.rest.Todo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RestTemplateMain {
    private final RestTemplate restTemplate;

    public void getTodos() {
        ResponseEntity<List<Todo>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/todos",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Todo>>() {
                });

        response.getBody().forEach(System.out::println);
    }

}
