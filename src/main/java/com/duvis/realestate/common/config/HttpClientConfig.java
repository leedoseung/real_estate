package com.duvis.realestate.common.config;

import com.duvis.realestate.common.logging.HttpLoggingInterceptor;
import com.duvis.realestate.common.module.DateTimeJacksonModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
public class HttpClientConfig {

    @Bean
    public RestTemplate restTemplateForPublicData() {
        ClientHttpRequestFactory clientHttpRequestFactory = clientHttpRequest();
        return restTemplate(clientHttpRequestFactory);
    }

    private ClientHttpRequestFactory clientHttpRequest() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient());
        factory.setReadTimeout(1500);
        factory.setConnectTimeout(1000);

        return new BufferingClientHttpRequestFactory(factory);
    }

    private CloseableHttpClient httpClient() {
        return HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager())
                .build();
    }

    private HttpClientConnectionManager poolingHttpClientConnectionManager() {
        return new PoolingHttpClientConnectionManager() {{
            setDefaultMaxPerRoute(256);
            setMaxTotal(1024);
        }};
    }


    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if(interceptors != null){
            interceptors.add(new HttpLoggingInterceptor());
        } else {
            interceptors = Arrays.asList(new HttpLoggingInterceptor());
        }

        restTemplate.setInterceptors(interceptors);
        restTemplate.getMessageConverters().stream()
                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter).forEach(converter -> {
                    MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                    jsonConverter.setObjectMapper(new ObjectMapper());
                });

        return restTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new DateTimeJacksonModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }
}
