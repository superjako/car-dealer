package com.sg.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @Title: UUIDUtil
 * @Description: UUID生成
 * @author: xz
 * @date 2019/11/18 0018 14:13
 */
public final class UUIDUtil {

    private static SecureRandom numberGenerator = new SecureRandom();

    public UUIDUtil() {
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generate16UUID() {
        byte[] randomBytes = new byte[8];
        numberGenerator.nextBytes(randomBytes);
        long msb = 0L;

        for(int i = 0; i < 8; ++i) {
            msb = msb << 8 | (long)(randomBytes[i] & 255);
        }

        return digits(msb >> 32, 8) + digits(msb >> 16, 4) + digits(msb, 4);
    }

    private static String digits(long val, int digits) {
        long hi = 1L << digits * 4;
        return Long.toHexString(hi | val & hi - 1L).substring(1);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 20; ++i) {
            System.out.println(generateUUID());
        }

    }
}
