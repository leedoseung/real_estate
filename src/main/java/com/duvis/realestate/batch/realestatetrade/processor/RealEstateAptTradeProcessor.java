package com.duvis.realestate.batch.realestatetrade.processor;

import com.duvis.realestate.batch.realestatetrade.dto.AptTradeDto;
import com.duvis.realestate.domain.entity.EstateRealAptTrx;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RealEstateAptTradeProcessor implements ItemProcessor<List<AptTradeDto>, List<EstateRealAptTrx>> {

    @Override
    public List<EstateRealAptTrx> process(List<AptTradeDto> item) throws Exception {
        return item.stream()
                .map(EstateRealAptTrx::new)
                .collect(Collectors.toList());
    }
}
