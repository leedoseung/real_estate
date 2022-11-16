package com.duvis.realestate.batch.realestatetrade.reader;

import com.duvis.realestate.batch.realestatetrade.common.adapter.OpenApiAdapter;
import com.duvis.realestate.batch.realestatetrade.common.constant.TradeType;
import com.duvis.realestate.batch.realestatetrade.dto.AptTradeDto;
import com.duvis.realestate.domain.entity.KoreaAddress;
import com.duvis.realestate.repository.AddressSiguRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RealEstateAptTradeReaderTest {

    @Autowired
    private AddressSiguRepository addressSiguRepository;

    @Autowired
    private OpenApiAdapter openApiAdapter;




    @Test
    public void itemReaderTest() throws Exception {
        //given
        String month = "202201";
        List<KoreaAddress> addressInfo = addressSiguRepository.findAll();
        List<AptTradeDto> result = new ArrayList<>();

        List<AptTradeDto> aptTradeHistory = openApiAdapter.getAptTradeHistory(addressInfo.get(0).getSidoCd(), month, TradeType.APT);

        for (AptTradeDto aptTradeDto : aptTradeHistory) {
            System.out.println("aptTradeDto = " + aptTradeDto);
        }

       // result.addAll(openApiAdapter.getAptTradeHistory(address.getSidoCd(), month, TradeType.APT));
        //when

        //then

    }
}