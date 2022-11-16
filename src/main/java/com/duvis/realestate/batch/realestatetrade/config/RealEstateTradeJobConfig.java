package com.duvis.realestate.batch.realestatetrade.config;

import com.duvis.realestate.batch.realestatetrade.dto.AptTradeDto;
import com.duvis.realestate.batch.realestatetrade.processor.RealEstateAptTradeProcessor;
import com.duvis.realestate.batch.realestatetrade.reader.RealEstateAptTradeReader;
import com.duvis.realestate.batch.realestatetrade.writer.RealEstateAptTradeWriter;
import com.duvis.realestate.domain.entity.EstateRealAptTrx;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@RequiredArgsConstructor
@Configuration
public class RealEstateTradeJobConfig {

    private static final String Job_NAME = "realStateTradeJob";

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final RealEstateAptTradeReader realEstateAptTradeReader;

    private final RealEstateAptTradeProcessor realEstateAptTradeProcessor;

    private final RealEstateAptTradeWriter realEstateAptTradeWriter;


    @Bean
    public Job realStateTradeJob(){
        return this.jobBuilderFactory.get(Job_NAME)
                .start(realEstateAptTradeStep())
                //.next(realEstateHouseTradeStep())
                //.next(realEstateMansionTradeStep())
                .build();
    }

    @Bean
    @JobScope
    public Step realEstateAptTradeStep() {
        return stepBuilderFactory.get("realEstateAptTradeStep")
                .<List<AptTradeDto>, List<EstateRealAptTrx>>chunk(1)
                .reader(realEstateAptTradeReader)
                .processor(realEstateAptTradeProcessor)
                .writer(realEstateAptTradeWriter)
                .build();
    }

    /*@Bean
    @JobScope
    public Step realEstateHouseTradeStep() {
        return stepBuilderFactory.get("realEstateHouseTradeStep")
                .chunk(1)
                .reader(realEstateHouseTradeReader)
                .processor(realEstateHouseTradeProcessor)
                .writer(realEstateHouseTradeWriter)
                .build();
    }

    @Bean
    @JobScope
    public Step realEstateMansionTradeStep() {
        return stepBuilderFactory.get("realEstateHouseTradeStep")
                .chunk(1)
                .reader(realEstateMansionTradeReader)
                .processor(realEstateMansionTradeProcessor)
                .writer(realEstateMansionTradeWriter)
                .build();
    }*/
}
