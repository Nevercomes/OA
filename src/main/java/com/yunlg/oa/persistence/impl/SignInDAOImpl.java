package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.SignIn;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.SignInDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class SignInDAOImpl extends AbstractDAO implements SignInDAO {
    @Override
    public SignIn getSignIn(String userId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from SignIn where userId='" + userId + "'";
            SignIn signIn = (SignIn) session.createQuery(hql).uniqueResult();
            transaction.commit();
            return signIn;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateStaffSignIn(SignIn signIn) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "update SignIn ssi set ssi.password='" + signIn.getPassword() + "', ssi.salt='" + signIn.getSalt() + "'" +
                    " where ssi.userId='" + signIn.getUserId() + "'";
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
