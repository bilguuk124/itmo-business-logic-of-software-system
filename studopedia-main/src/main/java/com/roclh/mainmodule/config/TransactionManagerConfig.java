package com.roclh.mainmodule.config;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {

    @Bean
    public BitronixTransactionManager bitronixTransactionManager(){
        return TransactionManagerServices.getTransactionManager();
    }

    @Bean(name = "transactionManager")
    public JtaTransactionManager transactionManager() {
        JtaTransactionManager transactionManager = new JtaTransactionManager();
        transactionManager.setTransactionManager(bitronixTransactionManager());
        return transactionManager;
    }
}
