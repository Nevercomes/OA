package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.AdminDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class AdminDAOImpl extends AbstractDAO implements AdminDAO {
    @Override
    public void saveAdmin(Admin admin, AdminSignIn adminSignIn) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            session.save(admin);
            adminSignIn.setAdminId(adminSignIn.getAdminId());
            session.save(adminSignIn);
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Admin getAdmin(String userId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from Admin where adminId='" + userId + "'";
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
