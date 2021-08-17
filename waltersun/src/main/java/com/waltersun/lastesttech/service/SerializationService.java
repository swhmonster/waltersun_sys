package com.waltersun.lastesttech.service;

import org.springframework.stereotype.Service;

/**
 * @author walter
 * @date 2021-08-17 11:12
 */
@Service
public interface SerializationService {
    /**
     * 序列化
     *
     * @param str 随机数据
     * @return array
     */
    byte[] encoder(String str);

    /**
     * 反序列化
     *
     * @param bytes 序列化数据
     * @return
     */
    Object decoder(byte[] bytes);
}
