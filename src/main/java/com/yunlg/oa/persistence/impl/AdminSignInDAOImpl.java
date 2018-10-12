package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.AdminSignInDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class AdminSignInDAOImpl extends AbstractDAO implements AdminSignInDAO {
    @Override
    public Admin adminSignIn(String userId, String password, String numbering) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "";
            Admin admin = (Admin) session.createQuery(hql).uniqueResult();
            session.flush();
            transaction.commit();
            return admin;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
