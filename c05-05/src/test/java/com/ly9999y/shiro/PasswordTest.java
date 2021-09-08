package com.ly9999y.shiro;

import org.junit.Test;

public class PasswordTest extends BaseTest {
    @Test
    public void testHashedCredentialsMatcherWithMyRealm2() {
        //使用testGeneratePassword生成的散列密码
        login("classpath:shiro-hashedCredentialsMatcher.ini", "liu", "123");
    }
}
