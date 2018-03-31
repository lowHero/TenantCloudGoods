package com.nz2dev.tenantcloudgoods.data.api.room.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by nz2Dev on 31.03.2018
 */
public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(long value) {
        return new Date(value);
    }

    @TypeConverter
    public static long fromDate(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

}
