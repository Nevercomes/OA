package com.yunlg.oa.persistence.impl;

import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.model.SignIn;
import com.yunlg.oa.domain.model.UserRoleMapping;
import com.yunlg.oa.persistence.AbstractDAO;
import com.yunlg.oa.persistence.UserDAO;
import com.yunlg.oa.utils.HibernateUtil;
import com.yunlg.oa.utils.RoleMapping;
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
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public User getStaff(String userId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from User where userId='" + userId + "'";
            User user = (User) session.createQuery(hql).uniqueResult();
            session.flush();
            transaction.commit();
            return user;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void batchSaveStaff(final List<User> userList, final List<SignIn> signInList) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    String sql = "insert into user_info (user_id, `name`, department, `position`) values (?, ?, ?, ?)";
                    PreparedStatement pState = connection.prepareStatement(sql);
                    for (User user : userList) {
                        pState.setString(1, user.getUserId());
                        pState.setString(2, user.getName());
                        pState.setInt(3, user.getDepartment());
                        pState.setInt(4, user.getPosition());
                        pState.addBatch();
                    }
                    pState.executeBatch();
                    String sql1 = "insert into user_pwd (`user_id`, password, `salt`) values (?, ?, ?)";
                    PreparedStatement pState1 = connection.prepareStatement(sql1);
                    for (SignIn signIn : signInList) {
                        pState1.setString(1, signIn.getUserId());
                        pState1.setString(2, signIn.getPassword());
                        pState1.setString(3, signIn.getSalt());
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

    @Override
    public void saveAdmin(User user, SignIn signIn) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            int pos = user.getPosition();
            int dep = user.getDepartment();
            int role = RoleMapping.getRole(dep, pos);
            if (role == -1)
                return;
            session.save(user);
            session.save(signIn);
            UserRoleMapping userRoleMapping = new UserRoleMapping();
            userRoleMapping.setRoleId(role);
            userRoleMapping.setUserId(user.getUserId());
            session.save(userRoleMapping);
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
    public List<User> getStaffList(int department) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            String hql = "from User where department=" + department;
            List<User> userList = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return userList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void singleSaveStaff(User user, SignIn signIn) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransaction(session);
        try {
            session.save(user);
            session.save(signIn);
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
