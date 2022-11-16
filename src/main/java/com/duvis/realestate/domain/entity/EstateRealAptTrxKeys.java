package com.duvis.realestate.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class EstateRealAptTrxKeys implements Serializable {

    private String tradeMonth;
    private String zipCode;
    private String aptName;
    private String jibun;
    private String floor;

}
