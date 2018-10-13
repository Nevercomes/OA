package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.exception.WorkServiceException;
import com.yunlg.oa.persistence.WorkPlanDAO;
import com.yunlg.oa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class WorkServiceImpl implements WorkService {

    private WorkPlanDAO workPlanDAO;

    @Autowired
    public WorkServiceImpl(WorkPlanDAO workPlanDAO) {
        this.workPlanDAO = workPlanDAO;
    }

    @Override
    public void submitWorkPlan(WorkPlan workPlan) throws WorkServiceException {
        try {
            if(workPlan.getPlanId() == 0)
                workPlanDAO.saveWorkPlan(workPlan);
            else
                workPlanDAO.updateWorkPlan(workPlan);
        } catch (PersistenceException pe) {
            throw new WorkServiceException(pe);
        }
    }
}
