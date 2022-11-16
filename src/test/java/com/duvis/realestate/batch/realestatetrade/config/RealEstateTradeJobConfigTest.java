package com.duvis.realestate.batch.realestatetrade.config;

import com.duvis.realestate.TestBatchConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;


@RunWith(SpringRunner.class)
@SpringBatchTest
@SpringBootTest(classes={RealEstateTradeJobConfig.class, TestBatchConfig.class})
class RealEstateTradeJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;


    @Before
    public void clearJobExecutions() {
        this.jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    public void JobParameter생성시점() throws Exception {
        //given
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString("month", "202002");

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(builder.toJobParameters());

        //then
        Assert.assertThat(jobExecution.getStatus(), is(BatchStatus.COMPLETED));
    }
}