package com.example.bangiaytablet.Class;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class NgayThang {
    LocalDate beforDate, afterDate;

    public NgayThang(String befordate, String afterdate) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        beforDate = LocalDate.parse(befordate, myFormatObj);
        afterDate = LocalDate.parse(afterdate, myFormatObj);
        if (!beforDate.isBefore(afterDate)) {
            beforDate = afterDate;
            afterDate = LocalDate.parse(befordate, myFormatObj);
        }
        //LocalDate.parse(String date, DataTimeFormatter format);
    }

    public NgayThang(LocalDate beforDate, LocalDate afterDate) {
        beforDate = beforDate;
        afterDate = afterDate;
    }

    public boolean comparisionDate(String parDate) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(parDate, myFormatObj);
        return beforDate.isBefore(date) && afterDate.isAfter(date);
    }

    public LocalDate getBeforDate() {
        return beforDate;
    }

    public void setBeforDate(LocalDate beforDate) {
        beforDate = beforDate;
    }

    public LocalDate getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(LocalDate afterDate) {
        afterDate = afterDate;
    }
}
