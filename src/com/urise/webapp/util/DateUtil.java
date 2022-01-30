package com.urise.webapp.util;

import com.urise.webapp.model.Organisation;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String date(Organisation.Position position) {
        String startDate = position.getStartDate() == null? "" : position.getStartDate().toString();
        String endDate = position.getEndDate() == null? "" : position.getEndDate().toString();
        return position.getEndDate().equals(NOW)? startDate + " - Сейчас" : startDate + " - " + endDate;
    }
}