package com.ly9999y.shiro.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener1 implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话过期：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("会话停止：" + session.getId());
    }
}
