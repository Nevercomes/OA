package com.yunlg.oa.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static SessionFactory sessionfactory = null;

    static {
        try {
            // 读取hibernate.cfg.xml文件
            Configuration cfg = new Configuration().configure();
            // 建立sessionFactory
            sessionfactory = cfg.buildSessionFactory();
        } catch (Exception e) {
            System.out.println("创建会话工厂失败");
            e.printStackTrace();
        }
    }

    public static Session getSession() throws HibernateException {
        Session session = threadLocal.get();
        if (session == null || !session.isOpen()) {
            if (sessionfactory == null) {
                rebuildSessionFactory();
            }
            // 取得session
            session = (sessionfactory != null) ? sessionfactory.openSession() : null;
            threadLocal.set(session);
        }
        return session;
    }

    public static void rebuildSessionFactory() {
        // TODO Auto-generated method stub
        try {
            Configuration cfg = new Configuration().configure();
            sessionfactory = cfg.buildSessionFactory();
        } catch (Exception e) {
            System.out.println("创建会话工厂失败");
            e.printStackTrace();
        }
    }

    // 获取SessionFactory对象
    public static SessionFactory getSessionFactory() {
        return sessionfactory;
    }

    // 关闭session
    public static void closeSession() throws HibernateException {
        Session session = threadLocal.get();
        threadLocal.set(null);
        if (session != null) {
            session.close();//关闭session
        }
    }
}
