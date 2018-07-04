package com.lfd.frontend.common.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by Administrator on 12/19/2016.
 */
//@Configuration
public class RocketMQConfig {
    @Value("rocket.mq.accessKey")
    private String accessKey;
    @Value("rocket.mq.secretKey")
    private String secretKey;
    @Value("rocket.mq.producerId")
    private String producerId;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean producer() {
        ProducerBean producer = new ProducerBean();

        Properties properties = new Properties();
        properties.setProperty("AccessKey", accessKey);
        properties.setProperty("SecretKey", secretKey);
        properties.setProperty("ProducerId", producerId);
        producer.setProperties(properties);

        return producer;
    }

//    @Bean(initMethod = "start", destroyMethod = "shutdown")
//    public TransactionProducerBean transactionProducer() {
//        TransactionProducerBean transactionProducer = new TransactionProducerBean();
//
//        Properties properties = new Properties();
//        properties.setProperty("AccessKey", accessKey);
//        properties.setProperty("SecretKey", secretKey);
//        properties.setProperty("ProducerId", producerId);
//
//        transactionProducer.setProperties(properties);
//
//        return transactionProducer;
//    }
}