package com.csn.csn.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
//1) 스프링 배치가 제공하는 어노테이션으로, 배치 인프라스트럭처를 부트스트랩하는데 사용된다.
//(인프라스트럭처 레이어 : ItemReader, ItemWriter 를 비롯해, 재시작과 관련된 문제를 해결할 수 있는 클래스와 인터페이스를 제공한다.)
//2) 이 어노테이션이 배치 인프라스트럭처를 위한 대부분의 스프링 빈 정의를 제공한다. 그렇기에 아래 컴포넌트를 직접 포함시킬 필요가 없다.
public class BatchJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
//    Job
//    배치 처리 과정을 하나의 단위로 만들어 표현한 객체
//    하나의 job 안에는 여러 step이 있다. 각 step을 배치의 기본 흐름대로 구현하다.
//    Job 객체를 만드는 빌더는 여러 개 있다. 여러 빌더를 통합 처리하는 공장인 JobBuilderFactory로 원하는 Job을 쉽게 만들 수 있다.
//    Job은 Step 또는 Flow 인스턴스의 컨테이너 역할을 하기 때문에 생성하기 전에 인스턴스를 전달받는다.

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public BatchConfigurer batchConfigurer() {
        return new InMemoryBatchConfigurer();
    }

    @Autowired
    private UpdateTop100CoinPriceTasklet updateTop100CoinPriceTasklet;

    @Bean
    public Job updateTop100CoinPriceJob() {
        return jobBuilderFactory.get("updateTop100CoinPrice")
                .start(updateTop100CoinPrice())
                .build();
    }

    @Bean
    public Step updateTop100CoinPrice() {
        return stepBuilderFactory.get("updateTop100CoinPrice")
                .tasklet(updateTop100CoinPriceTasklet)
                .build();
    }
}//end class()
