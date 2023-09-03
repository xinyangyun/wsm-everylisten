package com.example.wsmeverylisten.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR) //年
            .appendLiteral("/")
            .appendValue(ChronoField.MONTH_OF_YEAR) //月
            .appendLiteral("/")
            .appendValue(ChronoField.DAY_OF_MONTH) //日
            .appendLiteral(" ")
            .appendValue(ChronoField.HOUR_OF_DAY) //时
            .appendLiteral(":")
            .appendValue(ChronoField.MINUTE_OF_HOUR) //分
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE) //秒
//            .appendLiteral(".")
//            .appendValue(ChronoField.MILLI_OF_SECOND) //毫秒
            .toFormatter();


    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.parse("2023/4/27 12:23:43", dateTimeFormatter);

        System.out.println(localDateTime.format(dateTimeFormatter));

        System.out.println(LocalDateTime.now().minusMinutes(5));

        System.out.println(LocalDateTime.now().plusMinutes(5));

        System.out.println(ChronoUnit.DAYS.between(LocalDate.of(2023,4,23),
                LocalDate.of(2023,4,27)));

    }


}
