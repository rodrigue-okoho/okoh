package com.okoho.okoho.utils;

import java.util.Random;

public final class KeyUtil {

    private KeyUtil() {
    }

    public static String  randonKey(int size) {

        String[] arr = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "z", "e", "r", "t", "y", "u", "i", "o", "p",
                "q", "s", "d", "f", "g", "h", "j", "k", "l", "m", "w", "x", "c", "v", "b", "n" };
        int leftLimit = 48;
        int rightLimit = 122;
        Random randomGenerator = new Random();
        return randomGenerator.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}
