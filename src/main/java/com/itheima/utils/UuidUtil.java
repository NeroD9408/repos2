package com.itheima.utils;

import java.util.UUID;

public class UuidUtil {

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
