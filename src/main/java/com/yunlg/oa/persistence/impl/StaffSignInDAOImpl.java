package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.Staff;
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
    public Staff staffSignIn(String userId, String password) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "select staff from Staff staff, StaffSignIn signIn where staff.userId=signIn.userId and signIn.userId='" + userId + "' and signIn.password='" + password + "'";
            Staff staff = (Staff) session.createQuery(hql).uniqueResult();
            transaction.commit();
            return staff;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
