package com.duvis.realestate.batch.realestatetrade.reader;

import com.duvis.realestate.batch.realestatetrade.common.adapter.OpenApiAdapter;
import com.duvis.realestate.batch.realestatetrade.common.constant.TradeType;
import com.duvis.realestate.batch.realestatetrade.dto.AptTradeDto;
import com.duvis.realestate.batch.realestatetrade.support.RealEstateAptSupport;
import com.duvis.realestate.domain.entity.KoreaAddress;
import com.duvis.realestate.repository.AddressSiguRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@StepScope
@Component
public class RealEstateAptTradeReader implements ItemReader<List<AptTradeDto>> {

    private AddressSiguRepository addressSiguRepository;
    private OpenApiAdapter openApiAdapter;

    public RealEstateAptTradeReader(AddressSiguRepository addressSiguRepository, OpenApiAdapter openApiAdapter) {
        this.addressSiguRepository = addressSiguRepository;
        this.openApiAdapter = openApiAdapter;
    }

    @Value("#{jobParameters[month]}")
    private String month;

    @Override
    public List<AptTradeDto> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        log.debug("Reading the information of the next AptTrade");

        List<KoreaAddress> addressInfo = addressSiguRepository.findAll();
        List<AptTradeDto> result = new ArrayList<>();

        addressInfo.forEach(address -> {
            result.addAll(openApiAdapter.getAptTradeHistory(address.getSidoCd(), month, TradeType.APT));
        });

        return result;
    }
}
