/*
 * Copyright (c) 2011 Technology Ltd.
 * All rights reserved.
 * project: nxcrm
 * create: 2011-3-21
 * cvs: $Id: DateUtil.java,v 1.1 2012/01/05 08:22:47 lawever Exp $
 */
package com.yichuang.kyjd.commnd.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理 (2011-3-21 上午10:35:32)
 * 
 * @author zhaoyan
 * @version $Revision: 1.1 $
 */
public class DateUtil {

    /**
     * 日期加一天
     * 
     * @param date
     * @return
     */
    public static Date addOneDay(Date date) {
        return addMoreDay(date, 1);
    }

    /**
     * 日期增加n天
     * 
     * @param date
     * @param n
     * @return
     */
    public static Date addMoreDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, dateOfMonth + n);
        return calendar.getTime();
    }

    /**
     * 日期减一天
     * 
     * @param date
     * @return
     */
    public static Date decreaseOneDay(Date date) {
        return decreaseMoreDay(date, 1);
    }

    /**
     * 日期减少n天
     * 
     * @param date
     * @param n
     * @return
     */
    public static Date decreaseMoreDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, dateOfMonth - n);
        return calendar.getTime();
    }
    
    /**
     * 获取字符串日期格式
     * @param date
     * @param format
     * @return
     */
    public static String currDayData(Date date,String format){
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.format(date);
    }

    /** 得到一天的开始日期时间 */
    public static Date startTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    /** 得到一天的结束日期时间 */
    public static Date endTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);    
        return calendar.getTime();
    }
    
    /**
     * 字符串转换成日期类型数据
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date fromString(String dateStr,String format) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.parse(dateStr);
    }
    
    /**
     * 
     * @param dateStr
     * @param fromFormat
     * @param toFormat
     * @return
     * @throws ParseException 
     */
    public static String zhDateString(String dateStr,String fromFormat,String toFormat) throws ParseException{
    	Date date = fromString(dateStr, fromFormat);
    	return currDayData(date, toFormat);
    }
    /**
	 * 根据传过来日期和格式换类型格式化数据
	 * @param date
	 * @param type
	 * @return
	 */
	public static String  getDateFromFormat(Date date,String type){
		SimpleDateFormat dateForma=getSimpleDateFormatsType(type);
		return dateForma.format(date);
	}
	
	/**
	 * SimpleDateFormat
	 * @param type 
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormatsType(String type){
		SimpleDateFormat dateFormat = new SimpleDateFormat(type);//
		return dateFormat;
	}
    public static void main(String args[]) {
        System.out.println(endTimeOfDay(new Date()));
    }
}
