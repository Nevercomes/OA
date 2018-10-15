package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.AssessResultDAO;
import com.yunlg.oa.utils.HibernateUtil;
import com.yunlg.oa.utils.TimeUtil;
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
            Query query = session.createQuery(hql);
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

    @Override
    public void updateAssessResult(int department, int month, String words) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from AssessResult where department=" + department + " and month=" + month;
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            AssessResult assessResult = (AssessResult) query.uniqueResult();
            if (assessResult == null) {
                AssessResult assessResult1 = new AssessResult();
                assessResult1.setDepartment(department);
                assessResult1.setMonth(month);
                assessResult1.setWords(words);
                assessResult1.setModifyTime(TimeUtil.getCurrentDate());
                session.save(assessResult1);
            } else {
                String hql1 = "update AssessResult ar set ar.words='" + words + "' , ar.modifyTime=" + TimeUtil.getCurrentDate() +
                        " where ar.department=" + department + " and month=" + month;
                session.createQuery(hql1).executeUpdate();
            }
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
