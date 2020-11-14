package com.j.sm.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.jvm.hotspot.utilities.ObjectReader;

/**
 * @ClassName ResultEntity
 * @Description 返回结果实体
 * @Author orange
 * @Date 2020-11-14 22:48
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResultEntity {
    private int code;
    private String message;
    private Object data;
}
