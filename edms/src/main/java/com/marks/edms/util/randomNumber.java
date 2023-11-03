package com.marks.edms.util;

import java.util.Random;

public class randomNumber {
    public static void main(String[] args) {
        Random random = new Random();
        addStringPrefix(2, "0", String.valueOf(random.nextInt(100)));
    }

    private static String addStringPrefix(int length, String prefix, String s) {
        int strLen = (null == s) ? 0 : s.length();
        int needPrefixNum = length - strLen;
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < needPrefixNum; i++) {
            result.append(prefix);
        }

        result.append(s);

        return result.toString();
    }
}
