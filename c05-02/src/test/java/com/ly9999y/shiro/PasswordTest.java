package com.ly9999y.shiro;

import org.junit.Test;

public class PasswordTest extends BaseTest {
    @Test
    public void testPasswordServiceWithMyRealm() {

        login("classpath:shiro-passwordservice.ini", "wu", "123");
    }
}
