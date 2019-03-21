package com.vivian.swagger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * @author swzhang
 * @date 2019/1/22
 * @description
 */
public class Test {
    public static void main(String[] args) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> postEntity = new LinkedMultiValueMap<>();
        postEntity.add("status", "0");
        postEntity.add("patientPhone", "18538186791");
        postEntity.add("patientName", "张云方");
        postEntity.add("taskId", "3b93aba6-3c81-4c2c-9b7b-25203fc9d38a");
        postEntity.add("data", "{\"patientPhone\":\"18538186791\",\"patientName\":\"张云方\",\"taskId\":\"3b93aba6-3c81-4c2c-9b7b-25203fc9d38a\",\"formId\":\"e6ec5a3276a7455abbb6f4bb9584a7e8\",\"hospCode\":\"466000838\",\"answers\":[{\"questionId\":\"questionId\",\"options\":[{\"questionId\":\"questionId\",\"questionAnswer\":\"questionAnswer选项ID\"}]}]}");
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postEntity, headers);
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        try {
            System.out.println(JSON.toJSONString(postEntity));
            System.out.println(postEntity.toString());
            ResponseEntity<String> exchange = restTemplate.exchange("http://ai.lanniuh.com:3002/MeeInterface/CallPhoneCallBack", HttpMethod.POST, requestEntity, String.class);
            System.out.println(exchange.getBody());
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
