package com.flexible.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JwtTools {
    private static String secretString = "09x878yh3iu54niuewgyv8srnjdshfasdh877yuhIU&T7gyuwbf87HJGU*&lksguds9aguhdsiufgh&*g*&KJHIYhjbgg87FHKLAX4xpW5Jpcxco+H53fCk4Q=";

    public static String enCodePassword(String str) {
        Key key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
        String jws = Jwts.builder().setSubject(str).signWith(key).compact();
        return jws;
    }

    public static String generateToken(String str) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject(str).signWith(key).compact();
        return jws;
    }

    public static String defaultValue(String value) {
        if (value != null) return value;
        return "";
    }

    public static Integer defaultValue(Integer value) {
        if (value != null) return value;
        return 0;
    }

    public static BigDecimal defaultValue(BigDecimal value) {
        if (value != null) return value;
        return BigDecimal.valueOf(0);
    }

    public static <T> T defaultValue(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    // 获取序列号
    public static String getSerialId(Integer userId) {

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String serialId = "";
        String md5Rand = DigestUtils.md5DigestAsHex((Long.toString(System.currentTimeMillis()) + Math.random()).getBytes());
        serialId += time.format(formatter) + userId.toString();
        serialId += md5Rand.substring(0, 6);

        return serialId;
    }

    public static String getFormatTime(LocalDateTime time) {
        return JwtTools.getFormatTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getFormatTime(LocalDateTime time, String formatStr) {
        // LocalDateTime time = LocalDateTime.now();
        // "yyyyMMddHHmmss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
        return time.format(formatter);
    }

    // 字符串转 LocalDateTime
    public static LocalDateTime parseStringToDateTime(String time) {
        return JwtTools.parseStringToDateTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    // 字符串转 LocalDateTime
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }
}
