package com.lfd.frontend.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class J8DateUtils {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Date stringToDate(String dateStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
        return Date.from(dateTime.toInstant(ZoneOffset.ofHours(8)));
    }

    public static String datetimeFormat(Date date) {
        return dateTimeFormatter.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    public static LocalDateTime fromDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date fromLocalDateTime(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate localDateFromDate(Date date) {
        return LocalDate.from(fromDate(date).toLocalDate());
    }

    public static Date fromLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalTime localTimeFromDate(Date date) {
        return LocalTime.from(fromDate(date).toLocalTime());
    }

    public static Date fromLocalTime(LocalTime localTime) {
        return Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
    }
}
