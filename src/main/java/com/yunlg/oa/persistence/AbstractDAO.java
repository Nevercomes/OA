package com.yunlg.oa.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AbstractDAO {
    public Transaction getTransaction(Session session){
        if(session.getTransaction().isActive())
            return session.getTransaction();
        return session.beginTransaction();
    }
}
