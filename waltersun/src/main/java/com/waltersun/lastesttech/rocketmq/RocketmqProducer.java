package com.waltersun.lastesttech.rocketmq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author walter
 * @date 2021-08-03 20:15
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RocketmqProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public void send(String topic,String message){
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("rockemq 发送成功：{}", JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable e) {
                log.info("rockemq 发送失败", e);
            }
        });
    }
}
