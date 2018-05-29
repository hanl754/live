package com.homedun.live.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author hanliang.hl
 * @date 2018-05-29 下午9:47
 **/
public class RandomUtils {
    private RandomUtils() {}

    private static final char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String random(int len) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, chars.length - 1);
            sb.append(chars[index]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(random(32));
    }
}
