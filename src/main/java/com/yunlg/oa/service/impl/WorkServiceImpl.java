package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.domain.orm.WorkPlanORM;
import com.yunlg.oa.domain.wrapper.WorkPlanWrapper;
import com.yunlg.oa.exception.WorkServiceException;
import com.yunlg.oa.persistence.WorkPlanDAO;
import com.yunlg.oa.service.WorkService;
import com.yunlg.oa.utils.DepartmentMapping;
import com.yunlg.oa.utils.PositionMapping;
import com.yunlg.oa.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    private WorkPlanDAO workPlanDAO;

    @Autowired
    public WorkServiceImpl(WorkPlanDAO workPlanDAO) {
        this.workPlanDAO = workPlanDAO;
    }

    @Override
    public WorkPlanWrapper getWorkPlanWrapper(String userId, int month) throws WorkServiceException {
        try {
            WorkPlanORM workPlanORM = workPlanDAO.getWorkPlanORM(userId, month);
            WorkPlanWrapper workPlanWrapper = new WorkPlanWrapper();
            if(workPlanORM == null) {
                return workPlanWrapper;
            }
            WorkPlan workPlan = workPlanORM.getWorkPlan();
            User user = workPlanORM.getUser();
            workPlanWrapper.setWorkPlan(workPlan);
            workPlanWrapper.setName(user.getName());
            workPlanWrapper.setDepartment(DepartmentMapping.getDepartmentStr(DepartmentMapping.getDepartment(user.getDepartment())));
            workPlanWrapper.setPosition(PositionMapping.getPositionStr(PositionMapping.getPosition(user.getPosition())));
            return workPlanWrapper;
        } catch (PersistenceException pe) {
            throw new WorkServiceException(pe);
        }
    }

    @Override
    public void submitWorkPlan(WorkPlan workPlan) throws WorkServiceException {
        try {
            if(workPlan.getPlanId() == 0) {
//                workPlan.setMonth(TimeUtil.getBeforeMonth());
                workPlan.setModifyTime(TimeUtil.getCurrentDate());
                workPlanDAO.saveWorkPlan(workPlan);
            }
            else
                workPlanDAO.updateWorkPlan(workPlan);
        } catch (PersistenceException pe) {
            throw new WorkServiceException(pe);
        }
    }

    @Override
    public List<WorkPlanWrapper> getWorkPlanWrapperList(int department, int month) throws WorkServiceException {
        try {
            List<WorkPlanORM> ormList = workPlanDAO.getWorkPlanORMList(department, month);
            List<WorkPlanWrapper> workPlanWrapperList = new ArrayList<>();
            if(ormList == null) {
                return new ArrayList<>();
            }
            for(WorkPlanORM orm : ormList) {
                WorkPlanWrapper workPlanWrapper = new WorkPlanWrapper();
                WorkPlan workPlan = orm.getWorkPlan();
                User user = orm.getUser();
                workPlanWrapper.setWorkPlan(workPlan);
                workPlanWrapper.setName(user.getName());
                workPlanWrapper.setDepartment(DepartmentMapping.getDepartmentStr(DepartmentMapping.getDepartment(user.getDepartment())));
                workPlanWrapper.setPosition(PositionMapping.getPositionStr(PositionMapping.getPosition(user.getPosition())));
                workPlanWrapperList.add(workPlanWrapper);
            }
            return workPlanWrapperList;
        } catch (PersistenceException pe) {
            throw new WorkServiceException(pe);
        }
    }
}
