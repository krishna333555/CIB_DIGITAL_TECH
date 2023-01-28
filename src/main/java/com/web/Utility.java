package com.web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    public static String getCurrentTimeStamp() {
        String stamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        return stamp;
    }
}
