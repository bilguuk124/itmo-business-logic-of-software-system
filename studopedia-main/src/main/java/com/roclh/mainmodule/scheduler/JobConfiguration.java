package com.roclh.mainmodule.scheduler;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;


@Configuration
public class JobConfiguration {
    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * * * ?";

    @Bean(name = "approveArticle")
    public JobDetailFactoryBean jobApproveArticle(){
        return QuartzConfig.createJobDetail(ApproveJob.class, "Approve new requests");
    }

    @Bean(name = "approveArticleTrigger")
    public CronTriggerFactoryBean triggerFactoryBean(@Qualifier("approveArticle") JobDetail jobDetail){
        return QuartzConfig.createCronTrigger(jobDetail, CRON_EVERY_FIVE_MINUTES, "Approve new request Trigger");
    }


}
