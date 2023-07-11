package com.example.chatservice.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chatservice.service.FrontService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service("frontService")
public class FrontServiceImpl implements FrontService {
    @Value("${open.ai.model}")
    private String openAiModel;
    @Value("${open.ai.key}")
    private String key;
    @Override
    public String getGPTAnswer(String place) {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+ key);
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");

        String requestJson = String.format("{\n" +
                        "    \"model\": \"text-davinci-003\",\n" +
                        "    \"prompt\": \"I want to have a trip in %s, could you please give me a detailed traveling plan?\",\n" +
                        "    \"temperature\": 0, \n" +
                        "    \"max_tokens\": 2048\n" +
                        "}",place);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, httpHeaders);
        ResponseEntity<String> response = client.exchange("https://api.openai.com/v1/completions",HttpMethod.POST,entity,String.class);
//        System.out.println(response.getBody());
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        JSONArray choices = jsonObject.getJSONArray("choices");


        return choices.getJSONObject(0).getString("text");
    }

    @Override
    public String testFlask() {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        String requestData = "{\n" +
                "    \"model\": \"text-davinci-003\",\n" +
                "    \"prompt\": \"I want to have a trip in, could you please give me a detailed traveling plan?\",\n" +
                "    \"temperature\": 0, \n" +
                "    \"max_tokens\": 2048\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(requestData, httpHeaders);
        ResponseEntity<String> response = client.exchange("http://localhost:5000/test",HttpMethod.POST,entity,String.class);
        System.out.println(response.getBody());
        return null;
    }
}
