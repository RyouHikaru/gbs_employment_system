package com.tapioca.utils;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomDateFormatter implements Formatter<LocalDate> {
    private final String DATE_PATTERN = "yyyy-MM";

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        YearMonth yearMonth = YearMonth.parse(text, formatter);
        return yearMonth.atDay(1);
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
