package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.StaffDAO;
import com.yunlg.oa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StaffDAOImpl extends AbstractDAO implements StaffDAO {

    @Override
    public Staff getStaff(String userId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from Staff where userId='" + userId + "'";
            Staff staff = (Staff) session.createQuery(hql).uniqueResult();
            session.flush();
            transaction.commit();
            return staff;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void batchSaveStaff(final List<Staff> staffList, final List<StaffSignIn> staffSignInList) throws PersistenceException  {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    String sql = "insert into user_info (user_id, `name`, department, `position`) values (?, ?, ?, ?)";
                    PreparedStatement pState = connection.prepareStatement(sql);
                    for(Staff staff : staffList) {
                        pState.setString(1, staff.getUserId());
                        pState.setString(2, staff.getName());
                        pState.setInt(3, staff.getDepartment());
                        pState.setInt(4, staff.getPosition());
                        pState.addBatch();
                    }
                    pState.executeBatch();
                    String sql1 = "insert into user_pwd (`user_id`, password, `salt`) values (?, ?, ?)";
                    PreparedStatement pState1 = connection.prepareStatement(sql1);
                    for(StaffSignIn staffSignIn : staffSignInList) {
                        pState1.setString(1, staffSignIn.getUserId());
                        pState1.setString(2, staffSignIn.getPassword());
                        pState1.setString(3, staffSignIn.getSalt());
                        pState1.addBatch();
                    }
                    pState1.executeBatch();
                    connection.commit();
//                    connection.close();
                }
            });
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
