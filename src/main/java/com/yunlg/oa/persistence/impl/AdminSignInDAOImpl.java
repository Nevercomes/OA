package com.yunlg.oa.persistence.impl;

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
    public AdminSignIn getAdminSignIn(String userId, String numbering) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from AdminSignIn where adminId='" + userId + "' and numbering='" + numbering + "'";
            AdminSignIn adminSignIn = (AdminSignIn) session.createQuery(hql).uniqueResult();
            session.flush();
            transaction.commit();
            return adminSignIn;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAdminSignIn(AdminSignIn adminSignIn) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "update AdminSignIn asi set asi.password='" + adminSignIn.getPassword() + "', asi.salt='" + adminSignIn.getSalt() + "'" +
                    " where asi.adminId='" + adminSignIn.getAdminId() + "'" + " and numbering='" + adminSignIn.getNumbering() + "'";
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void forceUpdateAdminSignIn(AdminSignIn adminSignIn) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "update AdminSignIn asi set asi.password='" + adminSignIn.getPassword() + "', asi.salt='" + adminSignIn.getSalt() + "'" +
                    " where asi.adminId='" + adminSignIn.getAdminId() + "'";
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
