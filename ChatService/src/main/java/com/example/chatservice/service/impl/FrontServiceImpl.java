package com.example.chatservice.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chatservice.service.FrontService;
import com.example.chatservice.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Service("frontService")
public class FrontServiceImpl implements FrontService {
    @Value("${open.ai.model}")
    private String openAiModel;
    @Value("${open.ai.key}")
    private String key;

    @Resource
    private HttpRequest httpRequestClient;
    @Override
    public String getGPTAnswer(String place) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer "+ key);
        headers.put("Content-Type", "application/json;charset=UTF-8");

//        JSONObject requestJson = new JSONObject();
//        requestJson.put("model", "text-davinci-003");
//        requestJson.put("prompt", "I want to have a trip in " + place + ", could you please give me a detailed traveling plan?");
//        requestJson.put("temperature", "0");
//        requestJson.put("max_tokens", "2048");
//        String requestBody = requestJson.toJSONString();
        String requestJson = String.format("{\n" +
                        "    \"model\": \"text-davinci-003\",\n" +
                        "    \"prompt\": \"I want to have a trip in %s, could you please give me a detailed traveling plan?\",\n" +
                        "    \"temperature\": 0, \n" +
                        "    \"max_tokens\": 2048\n" +
                        "}",place);
        ResponseEntity<String> response = httpRequestClient.post(headers,"https://api.openai.com/v1/completions",requestJson);
        System.out.println(response.getBody());
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        JSONArray choices = jsonObject.getJSONArray("choices");

        //TODO: 转发到各个python服务端处理
        return choices.getJSONObject(0).getString("text");
    }

    @Override
    public String testFlask() {
        // customize your http headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        // write the http body you want to send
        String requestData = "{\n" +
                "    \"name\": \"yangjiajian\"\n" +
                "}";

        // invoke POST method in self-defined httpRequestClient
        ResponseEntity<String> response = httpRequestClient.post(headers,"http://localhost:5000/test",requestData);
        System.out.println(response.getBody());
        return response.getBody();
    }
}
