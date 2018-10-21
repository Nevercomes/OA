package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.domain.orm.WorkPlanORM;

import javax.persistence.PersistenceException;
import java.util.List;

public interface WorkPlanDAO {
    void saveWorkPlan(WorkPlan workPlan) throws PersistenceException;

    void updateWorkPlan(WorkPlan workPlan) throws PersistenceException;

    WorkPlanORM getWorkPlanORM(String userId, int month) throws PersistenceException;

    List<WorkPlanORM> getWorkPlanORMList(int department, int month) throws PersistenceException;
}
