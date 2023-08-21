package com.bruna.calabrese.calculator.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomStringService {

    private RestTemplate restTemplate = new RestTemplate();
    private static String URL = "https://www.random.org/strings/?num=1&len=32&digits=on&unique=on&format=plain&rnd=new&upperalpha=on&loweralpha=on";

    public String generateRandomString() {
        return restTemplate.getForObject(URL, String.class);
    }
}
