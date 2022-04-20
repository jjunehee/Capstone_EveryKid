package com.capstone.everykid.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentTime {
    //현재시간 리턴 (년도,월,일,시,분)
    public static String getCurrentTime() {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMddHHmm"; //hhmmss로 시간,분,초만 뽑기도 가능
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(today);
    }
}
