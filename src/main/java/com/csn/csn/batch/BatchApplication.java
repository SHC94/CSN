package com.csn.csn.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.csn.csn")*/
public class BatchApplication {

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobRestartException {

        /*SpringApplication app = new SpringApplication(BatchApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);

        ConfigurableApplicationContext ctx  = app.run(args);
        JobLauncher jobLauncher             = ctx.getBean(JobLauncher.class);

        if (args.length > 0) {
            Job job = ctx.getBean(args[0], Job.class);
            jobLauncher.run(job, new JobParameters());
        } else {
            log.error("배치 잡 이름을 입력해 주세요. EX) {}", "java -jar app.jar updateTop100CoinPriceJob");
        }
        System.exit(0);*/
    }//end main()

}//end class()
