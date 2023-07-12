package com.example.chatservice.utils;

import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Data
@Component
public class HttpRequest {
    private RestTemplate client = new RestTemplate();

    public ResponseEntity<String> post(Map<String, String> headers, String url, String requestBody){
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Map.Entry entry: headers.entrySet()){
            httpHeaders.add((String)entry.getKey(),(String)entry.getValue());
        }
        HttpEntity<String> entity = new HttpEntity<>(requestBody, httpHeaders);

        return client.exchange(url, HttpMethod.POST,entity,String.class);
    }
}
