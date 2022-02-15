package com.emard.mybatch.batch;


import com.emard.mybatch.batch.listener.JobCompletionNotificationListener;
import com.emard.mybatch.model.SysTrack;
import com.emard.mybatch.modelOracle.OracleSysTrack;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
    
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private OracleSysTrackItemReader bankTransactionReader;
    private OracleSysTrackItemWriter bankTransactionWriter;
    private BankTransactionProcessor bankTransactionProcessor;
    @Qualifier("sysTrackTransactionManager")
    private PlatformTransactionManager sysTrackTransactionManager;

    @Bean
    @Transactional
    public Job bankJobb(JobCompletionNotificationListener listener){
        Step step1 = stepBuilderFactory.get("step-load-data")
        .<OracleSysTrack, SysTrack> chunk(10)
        .reader(bankTransactionReader)
        .processor(bankTransactionProcessor) //avt 1 seul processor
        //.processor(compositeItemProcessor()) // plusieur processor
        .writer(bankTransactionWriter)
        .transactionManager(sysTrackTransactionManager)
        .build();

        return jobBuilderFactory.get("bank-data-loader-job")
        .listener(listener)
        .start(step1)
        .build();
    }
}
