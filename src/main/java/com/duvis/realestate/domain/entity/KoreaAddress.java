package com.duvis.realestate.domain.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "KOREA_ADDRESS_SIGU")
public class KoreaAddress {

    @Id
    @Column(name = "SIDO_CD")
    private Long sidoCd;

    @Column(name = "SIDO_NM")
    private String sidoNm;

    @Column(name = "SIGUNGU_NM ")
    private String sigunguNm;
}
