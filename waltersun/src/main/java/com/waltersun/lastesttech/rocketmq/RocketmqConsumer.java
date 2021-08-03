package com.waltersun.lastesttech.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author walter
 * @date 2021-08-03 20:14
 */
@ConditionalOnProperty(name = "switch.rocketmq.enable",havingValue ="true" )
@Component
@RocketMQMessageListener(
        nameServer = "${rocketmq.name-server}",
        topic = "my-topic",
        consumerGroup = "waltersun-consumer-group")
@Slf4j
public class RocketmqConsumer implements RocketMQListener<JSONObject> {
    @Override
    public void onMessage(JSONObject message) {
        log.info("rockemq consumer 消费了：{}", JSON.toJSONString(message));
    }
}
