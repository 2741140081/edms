package com.marks.edms.TestCase_001;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class BCryptPasswordEncoderTest {
    final static private String password = "123456";
//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @Test
    public void savePassword() {
        //encode()：对明文字符串进行加密
        //注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode1 = encoder.encode(password);
        System.out.println("encode1:" + encode1);
        String encode2 = encoder.encode(password);
        System.out.println("encode2:" + encode2);

        // matches()：对加密前和加密后是否匹配进行验证
        //用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），
        // 而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，然后将其与从数据库中查询到的密码hash值进行比较。
        // 如果两者相同，说明用户输入的密码正确。
        boolean matches1 = encoder.matches(password, encode1);
        System.out.println("matches1:" + matches1);
        boolean matches2 = encoder.matches(password, encode2);
        System.out.println("matches2:" + matches2);

    }
}
