package com.example.homeworksix.converters;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

public class DateConvert {

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? 0: date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long dateLong) {
        return dateLong == 0 ? new Date(0): new Date(dateLong);
    }
}
