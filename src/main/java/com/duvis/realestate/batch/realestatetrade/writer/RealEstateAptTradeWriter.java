package com.duvis.realestate.batch.realestatetrade.writer;

import com.duvis.realestate.domain.entity.EstateRealAptTrx;
import com.duvis.realestate.repository.RealEstateTrxAptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RealEstateAptTradeWriter implements ItemWriter<List<EstateRealAptTrx>> {

    private final RealEstateTrxAptRepository repository;

    @Override
    public void write(List<? extends List<EstateRealAptTrx>> items) throws Exception {
        log.debug("Received the information of {} array", items.size());
        log.info("아파트 실거래가 데이터 수신, DB 트랜잭션 시작. [거래 건수 : " + items.size() + " 건]");
        items.forEach(repository::saveAll);
        log.info("아파트 실거래가 데이터 수신, DB 트랜잭션 종료. [거래 건수 : " + items.size() + " 건]");
    }
}
