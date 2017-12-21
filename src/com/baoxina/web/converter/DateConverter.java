package com.baoxina.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义参数绑定
 * 实现将日期字符串转换为日期类型的对象
 * @author baoxina
 *
 */
public class DateConverter implements Converter<String, Date>{
	@Override
	public Date convert(String source) {
		if(source != null){
			if(source.length() > 0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					return sdf.parse(source);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}