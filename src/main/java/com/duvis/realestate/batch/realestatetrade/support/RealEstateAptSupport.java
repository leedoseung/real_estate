package com.duvis.realestate.batch.realestatetrade.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "service.open-api")
public class RealEstateAptSupport {

    private String serviceKey;
    private String aptUrl;
    private String houseUrl;
    private String mentionUrl;

}
