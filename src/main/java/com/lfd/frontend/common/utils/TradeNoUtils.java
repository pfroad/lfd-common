package com.lfd.frontend.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ryan on 1/28/16.
 */
public class TradeNoUtils {
    private static AtomicInteger atomic = new AtomicInteger();

    public static String generate(String cityCde, String date) {
        StringBuilder tradeNo = new StringBuilder();
        tradeNo.append(cityCde);
        tradeNo.append(date);
        tradeNo.append("000");
        tradeNo.append(RandomStringUtils.randomAlphanumeric(3));
        tradeNo.append(String.format("%07d", atomic.incrementAndGet()));
        return tradeNo.toString();
    }
}
