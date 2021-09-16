package com.waltersun.lastesttech.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author walter
 * @date 2021-09-16 17:25
 */
@Builder(toBuilder = true)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RedisLuaBean {
    /**
     *
     */
    private int id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String description;
}
