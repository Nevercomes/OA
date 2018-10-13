package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.exception.WorkServiceException;

public interface WorkService {
    void submitWorkPlan(WorkPlan workPlan) throws WorkServiceException;
}
