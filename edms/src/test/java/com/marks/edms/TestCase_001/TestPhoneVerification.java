package com.marks.edms.TestCase_001;

import com.marks.edms.util.PhoneVerification;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestPhoneVerification {
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
    @Test
    public void phoneValidate() {

        PhoneVerification phoneVerification = new PhoneVerification();
        List<String> phoneList = new ArrayList<>();
        phoneList.add("13185217412");
        phoneList.add("131 8521 7412");
        phoneList.add("131-8521-7412");
        phoneList.add("(131) 8521-7412");
        phoneList.add("(+86)13645678906");
        phoneList.add("+8613645678906");
        phoneList.add("0755-1234567");
        phoneList.add("010-12345678");
        phoneList.add("4001-201224");
        phoneList.add("211231233");
        phoneList.add("18602046968");
        phoneList.add("18928780421");
        phoneList.add("57320962");
        phoneList.add("11");
        phoneList.add("1111111");
        phoneList.add("010-57320962");
        phoneList.add("010-57320960");
        phoneList.add("15011534685");
        phoneList.add("1");
        phoneList.add("1111");
        phoneList.add("13260434853");
        phoneList.add("13922232982");
        phoneList.add("86 13801952368");
        phoneList.add("57320881");
        phoneList.add("18600586801");
        phoneList.add("13610086394");
        phoneList.add("881");
        phoneList.add("13760138008");
        phoneList.add("13392681527");
        phoneList.add("38010178");
        phoneList.add("020-38010178");
        phoneList.add("82877085");
        phoneList.add("57320810");
        phoneList.add("0755082877085");
        phoneList.add("010-57320810");
        phoneList.add("010-57320896");
        phoneList.add("57320896");
        phoneList.add("010-57320829");
        phoneList.add("010-57320995");
        for (int i = 0; i < phoneList.size(); i++) {
            boolean flag = phoneVerification.phoneValidate(phoneList.get(i));
            if (flag) {
                System.out.println("Verification passed:" + phoneList.get(i));
            }else {
                System.out.println("Verification failed:" + phoneList.get(i));
            }

        }


    }
}

