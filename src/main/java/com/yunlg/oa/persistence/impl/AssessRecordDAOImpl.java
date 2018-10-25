package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.AssessRecord;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.AssessRecordDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class AssessRecordDAOImpl extends AbstractDAO implements AssessRecordDAO {

    @Override
    public AssessRecord getAssessRecord() throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from AssessRecord order by assessRecordId desc ";
            List<AssessRecord> list = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return list.size()>0 ? list.get(0) : new AssessRecord();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAssessRecord(AssessRecord assessRecord) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            if(assessRecord.getAssessRecordId() == 0) {
                session.save(assessRecord);
            }else {
                String hql = "update AssessRecord ar set ar.year=?, ar.month=?, ar.startTime=?, ar.endTime=?, ar.modifyTime=? where ar.assessRecordId=?";
                Query query =  session.createQuery(hql);
                query.setParameter(0, assessRecord.getYear());
                query.setParameter(1, assessRecord.getMonth());
                query.setParameter(2, assessRecord.getStartTime());
                query.setParameter(3, assessRecord.getEndTime());
                query.setParameter(4, assessRecord.getModifyTime());
                query.setParameter(5, assessRecord.getAssessRecordId());
                query.executeUpdate();
            }
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
