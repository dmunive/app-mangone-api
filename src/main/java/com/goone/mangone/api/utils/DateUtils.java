package com.goone.mangone.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String PATTERN_YYYYMMDD_HYPHEN = "yyyy-MM-dd";

    public static String formatDatebyPatternYYYYMMDDHyphen(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_YYYYMMDD_HYPHEN);
        return simpleDateFormat.format(date);
    }

}
