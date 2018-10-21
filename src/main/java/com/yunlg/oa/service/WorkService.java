package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.domain.wrapper.WorkPlanWrapper;
import com.yunlg.oa.exception.WorkServiceException;

import java.util.List;

public interface WorkService {

    WorkPlanWrapper getWorkPlanWrapper(String userId, int month) throws WorkServiceException;

    void submitWorkPlan(WorkPlan workPlan) throws WorkServiceException;

    List<WorkPlanWrapper> getWorkPlanWrapperList(int department, int month) throws WorkServiceException;
}
