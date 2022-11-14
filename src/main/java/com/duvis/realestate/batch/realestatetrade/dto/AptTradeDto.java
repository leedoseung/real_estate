package com.duvis.realestate.batch.realestatetrade.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "item")
public class AptTradeDto {

    private String 거래금액;
    private String 건축년도;
    private String 년;
    private String 법정동;
    private String 아파트;
    private String 월;
    private String 일;
    private String 전용면적;
    private String 지번;
    private String 지역코드;
    private String 층;
}
