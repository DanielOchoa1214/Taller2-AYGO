package edu.aygo.taller2.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@Getter
@RequiredArgsConstructor
public class Facade {
    private static final String[] servers = new String[]{"172.31.20.166", "172.31.23.165", "172.31.16.231"};
    private static int currentServer = 0;

    private final RestTemplate restTemplate;

    @GetMapping
    public Resource getIndex(){
        return new ClassPathResource("/public/index.html");
    }

    @PutMapping("/name")
    public ResponseEntity<Map<String, String>> sendNewUserRequest(@RequestBody String name){
        ParameterizedTypeReference<Map<String, String>> responseType = new ParameterizedTypeReference<>() {};
        RequestEntity<String> request = RequestEntity.put(buildUrl())
                .accept(MediaType.APPLICATION_JSON)
                .body(name);
        return restTemplate.exchange(request, responseType);
    }

    private static int getNextRoundRobin(){
        int nextServer = currentServer % 3;
        currentServer++;
        return nextServer;
    }

    private static String buildUrl(){
        System.out.println("http://" + servers[getNextRoundRobin()] + ":8080/username");
        return "http://" + servers[getNextRoundRobin()] + ":8080/username";
    }
}
