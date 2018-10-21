package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.domain.orm.WorkPlanORM;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.WorkPlanDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

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

    @Override
    public WorkPlanORM getWorkPlanORM(String userId, int month) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "select new com.yunlg.oa.domain.orm.WorkPlanORM(wp, user) from WorkPlan wp, User user " +
                    "where wp.userId=user.userId and wp.userId='" + userId + "' and wp.month=" + month;
            WorkPlanORM orm = (WorkPlanORM) session.createQuery(hql).uniqueResult();
            session.flush();
            transaction.commit();
            return orm;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<WorkPlanORM> getWorkPlanORMList(int department, int month) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "select new com.yunlg.oa.domain.orm.WorkPlanORM(wp, user) from WorkPlan wp, User user " +
                    "where wp.userId=user.userId " + "and user.department=" + department + " and wp.month=" + month;
            List<WorkPlanORM> ormList = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return ormList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
