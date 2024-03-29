package com.marks.edms.util;

import java.util.regex.Pattern;

public class PhoneVerification {
    public boolean phoneValidate(String phoneNumber){
        if (phoneNumber != null && !phoneNumber.isEmpty()){
            boolean matches = Pattern.matches(
                    "^1[3-9]\\d{9}$|" +
                            "^1[3-9]\\d{1}[-\\s]\\d{4}[-\\s]\\d{4}$|" +
                            "^\\(1[3-9]\\d{1}\\)\\d{4}-\\d{4}$|" +
                            "^(?:\\(\\+\\d{2}\\)|\\+\\d{2})(\\d{11})$|" +
                            "^0\\d{3}-\\d{7}$|" +
                            "^0\\d{2}-\\d{8}$", phoneNumber);

            return matches;
        }
        return false;
    }
}
/**
 * "^1[3-9]\\d{9}$|"
 * // 匹配以1开头的11位数字手机号格式如：13185217412
 *
 * "^1[3-9]\\d{1}[-\\s]\\d{4}[-\\s]\\d{4}$|"
 * // 匹配以1开头的带区号的手机号，格式如：131 8521 7412 或 131-8521-7412
 *
 * "^\\(1[3-9]\\d{1}\\)\\d{4}-\\d{4}$|"
 * // 匹配以1开头的带区号的手机号，格式如：(131) 8521-7412
 *
 * "^(?:\\(\\+\\d{2}\\)|\\+\\d{2})(\\d{11})$|"
 * // 匹配国际格式的手机号，如：(+86)13645678906 或 +8613645678906
 *
 * "^0\\d{3}-\\d{7}$|"
 * // 匹配以0开头的带四位区号的座机号，格式如：0755-1234567
 *
 * "^0\\d{2}-\\d{8}$"
 * // 匹配以0开头的带三位区号的座机号，格式如：010-12345678
 */
