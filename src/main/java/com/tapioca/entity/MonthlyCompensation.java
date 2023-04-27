package com.tapioca.entity;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthlyCompensation {
    private int year;
    private String month;
    private BigDecimal totalAmount;

    public MonthlyCompensation() {
    }

    public MonthlyCompensation(int year, int month, BigDecimal totalAmount) {
        this.year = year;
        this.month = Month.of(month).getDisplayName(TextStyle.FULL, Locale.getDefault());
        this.totalAmount = totalAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "MonthlyCompensation{" +
                "month=" + month +
                ", year=" + year +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
