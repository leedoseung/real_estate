package com.duvis.realestate.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;


@SpringBootTest
public class RealEstateRestClientTest {

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Value("${open.api.public.data.serviceKey}")
    private String serviceKey;

    @Value("${open.api.public.data.apt_trade.url}")
    private String aptTradeUrl;

    @Value("${open.api.public.data.house_trade.url}")
    private String homeTradeUrl;

    @Value("${open.api.public.data.mention_trade.url}")
    private String mentionTradeUrl;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @DisplayName("공공데이터 Rest API 테스트")
    @Test
    public void api_test() throws Exception {

        URI uri = UriComponentsBuilder.fromHttpUrl(aptTradeUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("LAWD_CD", "11110")
                .queryParam("DEAL_YMD", "201512")
                .build()
                .encode()
                .toUri();

        //when
        mockServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        //then


    }


}
