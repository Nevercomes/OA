package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.AssessResultDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class AssessResultDAOImpl extends AbstractDAO implements AssessResultDAO {
    @Override
    public AssessResult getAssessResult(int department, int month) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from AssessResult where department=" + department + " and month=" + month;
            Query query =  session.createQuery(hql);
            query.setMaxResults(1);
            AssessResult assessResult = (AssessResult) query.uniqueResult();
            session.flush();
            transaction.commit();
            return assessResult;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
