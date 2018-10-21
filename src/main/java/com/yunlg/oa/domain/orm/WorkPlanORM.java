package com.yunlg.oa.domain.orm;

import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.model.WorkPlan;
import lombok.Data;

@Data
public class WorkPlanORM {
    private WorkPlan workPlan;
    private User user;

    public WorkPlanORM() {

    }

    public WorkPlanORM(WorkPlan workPlan, User user) {
        this.workPlan = workPlan;
        this.user = user;
    }
}
