package org.message.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class DateUtils {
	private static Logger log = Logger.getLogger(DateUtils.class);
	
	public static final String YYYY_YEAR_MM_MONTH_DD_DATE = "yyyy年MM月dd日";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_BIAS_MM_BIAS_DD = "yyyy/MM/dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String SS = "ss";
	
	
	public static String strToStr(String date, String original_format,String transform_format) {
		DateFormat simple = new SimpleDateFormat(original_format);
		DateFormat df =  new SimpleDateFormat(transform_format);
		Date d = null;
		try {
			d = simple.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage(),e);
		}
		return df.format(d).toString();
	}

	/**
	 * 由String转成Date类型时，参数date的format格式必须和参数format的格式保持一致�?
	 * **/
	
	public static Date strToDate(String date, String format) throws ParseException {
		DateFormat simple = new SimpleDateFormat(format);
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		Date d = null;
		d = simple.parse(date);
		return d;
	}
	
	/**
	 * 由Date转成String类型
	 * @date 
	 * @dateFormat - 转换成String的date-format格式
	 */
	public static String dateToStr(Date date,String dateFormat){
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			String d = sdf.format(date);
			return d;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException("The error is getDay."+e.getCause());
		}
	}
	
	/**
	 * greate than or equals 大于等于(date >= other)
	 * @param date 
	 * @param other
	 * @return
	 */
	public static boolean ge(Date date, Date other){
		if(date.after(other) || date.equals(other))
			return true;
		return false;
	}
	
	/**
	 * less than or equals 小于等于(date <= other)
	 * @param date
	 * @param other
	 * @return
	 */
	public static boolean le(Date date,Date other){
		if(date.before(other)|| date.equals(other))
			return true;
		return false;
	}
	
}
