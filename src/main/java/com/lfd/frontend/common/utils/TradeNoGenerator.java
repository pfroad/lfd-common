package com.lfd.frontend.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;

public class TradeNoGenerator {
	public static final long ID_START = 1000000000l;
	
	public static String generateSimple(Long userId) {
		StringBuilder no = new StringBuilder();
		no.append(timePart());
		no.append(generateUserIdentify(userId));
		no.append(RandomStringUtils.randomNumeric(3));
		return no.toString();
	}

	public static String generateWithCarNo(String carNo) {
		StringBuilder no = new StringBuilder();
		no.append(timePart());
		no.append(carNo.substring(carNo.length() - 4));
		no.append(RandomStringUtils.randomNumeric(3));
		return no.toString();
	}
	
	public static String generateUserIdentify(Long userId) {
		String tmp = String.valueOf(ID_START + userId);
		return tmp.substring(tmp.length() - 4);
	}
	
	public static String timePart() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());

		StringBuilder timeStr = new StringBuilder();
		timeStr.append(cal.get(Calendar.YEAR));
		timeStr.append(String.valueOf(cal.get(Calendar.MONTH) + 101).substring(1));
		timeStr.append(String.valueOf(cal.get(Calendar.DAY_OF_MONTH) + 100).substring(1));
		timeStr.append(String.valueOf(cal.get(Calendar.HOUR_OF_DAY) + 100).substring(1));
		timeStr.append(String.valueOf(cal.get(Calendar.MINUTE) + 100).substring(1));
		timeStr.append(String.valueOf(cal.get(Calendar.SECOND) + 100).substring(1));
		timeStr.append(String.valueOf(cal.get(Calendar.MILLISECOND) + 1000).substring(1));
		
		return timeStr.toString();
	}

	public static String generatePrepayId(String tradeNo) {
		return HashUtils.md5(tradeNo + System.currentTimeMillis());
	}
//	public static void main(String[] args) {
//		System.out.println(generateSimple(35809l));
//	}
}