package com.fz.admin.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpCore {
    private final Logger log = LoggerFactory.getLogger(HttpCore.class);

    @Autowired
    private RestTemplate restTemplate;

    public <T> T get(String strUrl,ParameterizedTypeReference<T> parameterizedTypeReference)
    {
        ResponseEntity<T> result=restTemplate.exchange(strUrl,HttpMethod.GET,null,parameterizedTypeReference);
        return result.getBody();
    }
    public <T> T post(String strUrl,String data,Class<T> clazz)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(data, headers);
        ResponseEntity<T> result=restTemplate.postForEntity(strUrl,entity,clazz);
        return result.getBody();
    }
    public <T> T post(String strUrl,String data,ParameterizedTypeReference<T> parameterizedTypeReference)
    {
        return post(strUrl,data,HttpMethod.POST,parameterizedTypeReference);
    }
    public <T> T post(String strUrl,String data,HttpMethod httpMethod,ParameterizedTypeReference<T> parameterizedTypeReference)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(data, headers);
        ResponseEntity<T> result=restTemplate.exchange(strUrl,httpMethod,entity,parameterizedTypeReference);
        return result.getBody();
    }
}
