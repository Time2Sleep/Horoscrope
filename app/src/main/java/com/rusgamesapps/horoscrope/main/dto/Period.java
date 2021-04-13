package com.rusgamesapps.horoscrope.main.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public enum Period {
    DAILY(new SimpleDateFormat("yyyy-MM-dd", RuLocale.LOCALE)),

    WEEKLY(new SimpleDateFormat("YYYY-ww", RuLocale.LOCALE)),

    MONTHLY(new SimpleDateFormat("yyyy-MM", RuLocale.LOCALE)),

    LUNAR(new SimpleDateFormat("yyyy-MM-dd", RuLocale.LOCALE));

    final SimpleDateFormat format;

    Period(SimpleDateFormat format) {
        this.format = format;
    }

    public Date parseDate(String stringDate) throws ParseException {
        stringDate = stringDate.replace("W", "");
        return format.parse(stringDate);
    }

    public String fromDate(Date date) {
        return format.format(date);
    }

    public static Period fromInt(int value) {
        switch (value) {
            case 2:
                return WEEKLY;
            case 3:
                return MONTHLY;
            default:
                return DAILY;
        }
    }
}
