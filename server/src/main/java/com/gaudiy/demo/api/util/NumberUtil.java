package com.gaudiy.demo.api.util;

import org.springframework.stereotype.Component;

//数字関係用の方法
@SuppressWarnings("unchecked")
@Component
public class NumberUtil {
    //objectからlong変換
    public static Long toLong(Object data){
        return Long.parseLong(String.valueOf(data));
    }
}
