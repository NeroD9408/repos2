package com.itheima.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Converter<S,T>
 *     S:接收数据类型  string
 *     T:要转换成目标类型 Date
 */
public class StringToDate implements Converter<String, Date> {
    /**
     * source:接收页面数据 2000-10-10
     */
    public Date convert(String source) {
       // SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf=simpleDateFormat(source);
        Date date=null;
        try {
            date = sf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public SimpleDateFormat simpleDateFormat(String source){
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", source)) { // yyyy-MM-dd
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}$", source)) { // yyyy-MM-dd HH-mm-ss
            sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        } else if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", source)) { // yyyy-MM-dd HH:mm:ss
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", source)) { // yyyy-MM-dd HH:mm
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }else if (Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", source)) { // yyyy/MM/dd
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        } else if (Pattern.matches("^\\d{4}/\\d{2}/\\d{2} \\d{2}/\\d{2}/\\d{2}$", source)) { // yyyy/MM/dd HH/mm/ss
            sdf = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
        }  else if (Pattern.matches("^\\d{4}\\d{2}\\d{2}$", source)) { // yyyyMMdd
            sdf = new SimpleDateFormat("yyyyMMdd");
        }  else if (Pattern.matches("^\\d{4}\\d{2}\\d{2} \\d{2}\\d{2}\\d{2}$", source)) { // yyyyMMdd HHmmss
            sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        } else if (Pattern.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$", source)) { // yyyy.MM.dd
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        }  else if (Pattern.matches("^\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}\\.\\d{2}\\.\\d{2}$", source)) { // yyyy.MM.dd HH.mm.ss
            sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        }else{
            System.out.println("TypeMismatchException");
            throw new RuntimeException();
        }
        return sdf;
    }
}
