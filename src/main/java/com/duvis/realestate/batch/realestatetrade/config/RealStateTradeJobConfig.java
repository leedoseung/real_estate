package com.duvis.realestate.batch.realestatetrade.config;

import com.duvis.realestate.batch.realestatetrade.reader.RealEstateAptTradeReader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class RealStateTradeJobConfig {

    private static final String Job_NAME = "realStateTradeJob";

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final RealEstateAptTradeReader realEstateAptTradeReader;


    @Bean
    public Job realStateTradeJob(){
        return this.jobBuilderFactory.get(Job_NAME)
                .start(realEstateAptTradeStep())
                .next(realEstateHouseTradeStep())
                .next(realEstateMansionTradeStep())
                .build();
    }

    @Bean
    @JobScope
    public Step realEstateAptTradeStep() {
        return stepBuilderFactory.get("realEstateAptTradeStep")
                .chunk(1)
                .reader(realEstateAptTradeReader)
                .processor(realEstateAptTradeProcessor)
                .writer(realEstateAptTradeWriter)
                .build();
    }

    @Bean
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
    }
}
