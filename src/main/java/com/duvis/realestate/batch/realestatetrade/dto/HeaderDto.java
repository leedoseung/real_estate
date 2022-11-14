package com.duvis.realestate.batch.realestatetrade.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "header")
public class HeaderDto {
    private int resultCode;
    private String resultMessage;
}
