package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.StaffSignInDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class StaffSignInDAOImpl extends AbstractDAO implements StaffSignInDAO {
    @Override
    public StaffSignIn getStaffSignIn(String userId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from StaffSignIn where userId=" + userId;
            StaffSignIn staffSignIn = (StaffSignIn) session.createQuery(hql).uniqueResult();
            transaction.commit();
            return staffSignIn;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateStaffSign(StaffSignIn staffSignIn) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "update StaffSignIn ssi set ssi.password='" + staffSignIn.getPassword() + "', ssi.salt='" + staffSignIn.getSalt() + "'" +
                    " where ssi.userId='" + staffSignIn.getUserId() + "'";
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
