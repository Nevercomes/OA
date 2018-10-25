package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.InterceptorDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class InterceptorDAOImpl extends AbstractDAO implements InterceptorDAO {

    @Override
    public List<String> getAuthCodeList(String userId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "select auth.authCode from Permission auth, User user, Role role, UserRoleMapping urm, RolePermissionMapping ram " +
                    "where user.userId='" + userId + "' and urm.userId=user.userId and role.id=urm.roleId and ram.roleId=role.id " +
                    "and auth.authCode=ram.authCode";
            List<String> authCodeList = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return authCodeList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Integer> getAuthDepNum(String authCode) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "select department from Permission where authCode='" + authCode + "'";
            List<Integer> depList = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return depList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
