package com.ruoyi.common.utils;

import java.util.Random;

public class UserLogin {
    private static final String PREFIX = "distance_";
    private static final int LENGTH = 8;


    public static String generateDefaultAccount() {
        StringBuilder sb = new StringBuilder(PREFIX);

        // 生成5个随机的数字和字母组合
        for (int i = 0; i < LENGTH; i++) {
            char randomChar = generateRandomChar();
            sb.append(randomChar);
        }

        return sb.toString();
    }

    private static char generateRandomChar() {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int randomIndex = random.nextInt(characters.length());
        return characters.charAt(randomIndex);
    }
}
