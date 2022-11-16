package com.duvis.realestate.batch.realestatetrade.common.adapter;

import com.duvis.realestate.batch.realestatetrade.common.constant.TradeType;
import com.duvis.realestate.batch.realestatetrade.common.exception.NoTradeTypeException;
import com.duvis.realestate.batch.realestatetrade.dto.AptTradeDto;
import com.duvis.realestate.batch.realestatetrade.dto.ResponseDto;
import com.duvis.realestate.batch.realestatetrade.support.RealEstateAptSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class OpenApiAdapter {

    @Autowired
    @Qualifier("restTemplateForPublicData")
    private RestTemplate restTemplate;

    @Autowired
    private RealEstateAptSupport support;



    public List<AptTradeDto> getAptTradeHistory(Long siguCode, String dealYmd, TradeType type) {
        try {

            String url = null;
            switch (type) {
                case APT -> url = support.getAptUrl();
                case HOUSE -> url = support.getHouseUrl();
                case MENTION -> url = support.getMentionUrl();
                default -> throw new NoTradeTypeException("tradeType을 지정하지 않았습니다.");
            }

            URI uri = UriComponentsBuilder
                    .fromHttpUrl(url)
                    .queryParams(buildParameters(siguCode, dealYmd))
                    .build(true)
                    .toUri();

            ResponseDto response = restTemplate.getForObject(uri, ResponseDto.class);
            assert response != null;
            return response.getBody().getItems();
        }catch (HttpClientErrorException ex) {
            log.error("요청이 정상적으로 처리되지 못했습니다.\n상태코드 : {} , 응답메세지 : {}", ex.getStatusText(), ex.getMessage());
            return null;
        }
    }

    private MultiValueMap<String, String> buildParameters(Long siguCode, String dealYmd) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("serviceKey", support.getServiceKey());
        params.add("LAWD_CD", String.valueOf(siguCode));
        params.add("DEAL_YMD", dealYmd);

        return params;
    }
}
