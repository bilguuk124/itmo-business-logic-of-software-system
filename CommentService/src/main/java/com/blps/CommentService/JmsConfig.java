package com.blps.CommentService;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfig {

    @Bean
    public ConnectionFactory getConnectionFactory() {
        return new ActiveMQConnectionFactory();
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(getConnectionFactory());
        template.setPubSubDomain(false);
        return template;
    }
}
