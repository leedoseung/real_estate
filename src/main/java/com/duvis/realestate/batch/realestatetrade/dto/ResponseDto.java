package com.duvis.realestate.batch.realestatetrade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@XmlRootElement(name="response")
public class ResponseDto {

    private HeaderDto header;
    private AptTradeItemsDto body;

}


