package com.example.demo.sender.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class TimestampConversionUtils {
	
	public static final Logger LOG = LoggerFactory.getLogger(TimestampConversionUtils.class);
	
	public static long getTimeConverasion(Object instanceOfTime) {
		long time = 0;
		try {
			if (instanceOfTime instanceof java.sql.Timestamp) {
				java.sql.Timestamp ts = ((java.sql.Timestamp) instanceOfTime);
				time = ts.getTime(); 
			} else if (instanceOfTime instanceof java.time.Instant) {
				java.sql.Timestamp ts = (java.sql.Timestamp.from((java.time.Instant) instanceOfTime));
				time = ts.getTime(); 
			}
		} catch (Exception e) {
			LOG.error("Exception occured while converting time object... {}", e);
			throw new ClassCastException(
					"Cannot cast instance of time neither into java.sql.timestamp nor java.time.Instant");
		}
		return time;
	}
}