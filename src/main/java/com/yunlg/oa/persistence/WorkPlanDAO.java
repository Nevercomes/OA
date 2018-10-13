package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.WorkPlan;

import javax.persistence.PersistenceException;

public interface WorkPlanDAO {
    void saveWorkPlan(WorkPlan workPlan) throws PersistenceException;

    void updateWorkPlan(WorkPlan workPlan) throws PersistenceException;
}
