package com.ly9999y.shiro;

import org.junit.Test;

public class PasswordTest extends BaseTest {
    @Test
    public void testPasswordServiceWithJdbcRealm() {

        login("classpath:shiro-jdbc-passwordservice.ini", "wu", "123");
    }
}
