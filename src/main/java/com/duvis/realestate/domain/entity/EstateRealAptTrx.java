package com.duvis.realestate.domain.entity;

import com.duvis.realestate.batch.realestatetrade.dto.AptTradeDto;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Setter
@Entity
@NoArgsConstructor
@Table(name ="ESTATE_REAL_TRX_APT")
@IdClass(EstateRealAptTrxKeys.class)
public class EstateRealAptTrx {

    @Column(name = "TRADE_MONTH")
    @Id
    private String tradeMonth;

    @Column(name = "TRADE_AMOUNT")
    private String tradeAmount;

    @Column(name = "BUILDING_YEAR")
    private String buildingYear;

    @Column(name = "TRADE_YEAR")
    private String tradeYear;

    @Column(name = "TRADE_DAY")
    private String tradeDay;

    @Column(name = "LEGAL_DONG")
    private String legalDong;

    @Id
    @Column(name = "APT_NAME")
    private String aptName;

    @Column(name = "APT_SIZE")
    private String aptSize;

    @Id
    @Column(name = "JIBUN")
    private String jibun;

    @Id
    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Id
    @Column(name = "FLOOR")
    private String floor;

    @Column(name = "HOUSE_TYPE")
    private HouseType type;

    @Builder
    public EstateRealAptTrx(String tradeMonth, String tradeAmount, String buildingYear, String tradeYear, String tradeDay, String legalDong, String aptName, String aptSize, String jibun, String zipCode, String floor, HouseType type) {
        this.tradeMonth = tradeMonth;
        this.tradeAmount = tradeAmount;
        this.buildingYear = buildingYear;
        this.tradeYear = tradeYear;
        this.tradeDay = tradeDay;
        this.legalDong = legalDong;
        this.aptName = aptName;
        this.aptSize = aptSize;
        this.jibun = jibun;
        this.zipCode = zipCode;
        this.floor = floor;
        this.type = type;
    }

    public EstateRealAptTrx(AptTradeDto trx) {
         builder().tradeMonth(trx.get월())
                .tradeAmount(trx.get거래금액())
                .buildingYear(trx.get건축년도())
                .tradeYear(trx.get년())
                .tradeDay(trx.get일())
                .legalDong(trx.get법정동())
                .aptName(trx.get아파트())
                .aptSize(trx.get전용면적())
                .jibun(trx.get지번())
                .zipCode(trx.get지역코드())
                .floor(trx.get층())
                .type(HouseType.APT)
                .build();
    }
}
