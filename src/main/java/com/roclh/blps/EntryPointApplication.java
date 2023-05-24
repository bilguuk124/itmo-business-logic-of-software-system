package com.roclh.blps;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.jms.ConnectionFactory;
import java.util.stream.Stream;

@SpringBootApplication
public class EntryPointApplication {

    private static final Logger log = LogManager.getLogger(EntryPointApplication.class);
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(EntryPointApplication.class, args);
    }

    public static void displayAllBeans(String filter) {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        Stream.of(allBeanNames).filter(
                (val) -> val.contains(filter)
        ).forEach(log::info);
    }


}
