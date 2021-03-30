package com.example.horoscrope.main.dto;

public class GetHoroscopeDto {
    String period;
    String date;
    String sign;

    public GetHoroscopeDto(String period, String date, String sign) {
        this.period = period;
        this.date = date;
        this.sign = sign;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
