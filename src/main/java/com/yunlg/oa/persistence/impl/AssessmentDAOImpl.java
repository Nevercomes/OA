package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.orm.ViewAssessORM;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.AssessmentDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class AssessmentDAOImpl extends AbstractDAO implements AssessmentDAO {
    @Override
    public Assessment getAssessment(String userId, int month) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from Assessment where userId='" + userId + "' and month=" + month;
            Query query = session.createQuery(hql);
            List<Assessment> list = query.list();
            session.flush();
            transaction.commit();
            return list.size() == 0 ? null : list.get(0);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAssessment(Assessment assessment) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "update Assessment ss set ss.workRegular=?, ss.workOutPlan=?, ss.workOther=?, ss.workExpanse=?, ss.workPlanSimple=?, ss.remark=?, ss.workModifyTime=?" +
                    " where ss.assessId=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, assessment.getWorkRegular());
            query.setParameter(1, assessment.getWorkOutPlan());
            query.setParameter(2, assessment.getWorkOther());
            query.setParameter(3, assessment.getWorkExpanse());
            query.setParameter(4, assessment.getWorkPlanSimple());
            query.setParameter(5, assessment.getRemark());
            query.setParameter(6, assessment.getWorkModifyTime());
            query.setParameter(7, assessment.getAssessId());
            query.executeUpdate();
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void saveAssessment(Assessment assessment) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            session.save(assessment);
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Assessment> getAssessmentList(int department, int month) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql;
            if (department == 0) {
                hql = "from Assessment where month=" + month + " order by assessHeadScore+assessDirectorScore desc ";
            } else {
                hql = "select ss from Assessment ss, Staff staff where ss.month=" + month + " and ss.userId=staff.userId and staff.department=" + department
                + " order by ss.assessHeadScore+ss.assessDirectorScore desc ";
            }
            List<Assessment> list = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return list;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void evaluateAssessment(Assessment assessment)throws PersistenceException  {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "update Assessment ss set ss.assessHeadEva=?, ss.assessHeadScore=?, ss.assessDirectorEva=?, ss.assessDirectorScore=?,ss.remark=? ,ss.assessModifyTime=? " +
                    "where ss.assessId=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, assessment.getAssessHeadEva());
            query.setParameter(1, assessment.getAssessHeadScore());
            query.setParameter(2, assessment.getAssessDirectorEva());
            query.setParameter(3, assessment.getAssessDirectorScore());
            query.setParameter(4, assessment.getRemark());
            query.setParameter(5, assessment.getAssessModifyTime());
            query.setParameter(6, assessment.getAssessId());
            query.executeUpdate();
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<ViewAssessORM> getViewResultORMList(int department, int month) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql;
            if(department == 0) {
                hql = "select new com.yunlg.oa.domain.orm.ViewAssessORM(ss, staff) from Assessment ss, Staff staff where ss.userId=staff.userId" +
                " order by ss.assessHeadScore+ss.assessDirectorScore desc ";
            } else {
                hql = "select new com.yunlg.oa.domain.orm.ViewAssessORM(ss, staff) " +
                        "from Assessment ss, Staff staff where ss.month=" + month + " and ss.userId=staff.userId and staff.department=" + department +
                " order by ss.assessHeadScore+ss.assessDirectorScore desc ";
            }
            Query query = session.createQuery(hql);
            List<ViewAssessORM> ormList = query.list();
            session.flush();
            transaction.commit();
            return ormList;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
