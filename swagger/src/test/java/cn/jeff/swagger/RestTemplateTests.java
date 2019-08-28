package cn.jeff.swagger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import cn.jeff.swagger.controller.IndexController;
import org.apache.commons.collections.map.MultiValueMap;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author swzhang
 * @date 2019/1/22
 * @description
 */
public class RestTemplateTests {

    @Test
    public void testHttp() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        org.apache.commons.collections.map.MultiValueMap postEntity = new org.apache.commons.collections.map.MultiValueMap();
        postEntity.put("status", "0");
        postEntity.put("patientPhone", "12");
        postEntity.put("patientName", "321");
        postEntity.put("taskId", "123");
        postEntity.put("data", 1);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(postEntity, headers);
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setMessageConverters(Collections.singletonList(new FastJsonHttpMessageConverter()));

        try {
            restTemplate.postForObject("http://localhost:8000/callBack", requestEntity, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
