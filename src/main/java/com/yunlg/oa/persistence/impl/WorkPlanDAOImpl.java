package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.WorkPlanDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class WorkPlanDAOImpl extends AbstractDAO implements WorkPlanDAO {
    @Override
    public void saveWorkPlan(WorkPlan workPlan) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            session.save(workPlan);
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateWorkPlan(WorkPlan workPlan) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "update WorkPlan wp set wp.content='" + workPlan.getContent() + "', wp.modifyTime=" + workPlan.getModifyTime();
            session.createQuery(hql).executeUpdate();
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
