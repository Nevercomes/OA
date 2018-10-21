package com.yunlg.oa.domain.wrapper;

import com.yunlg.oa.domain.model.WorkPlan;
import lombok.Data;

@Data
public class WorkPlanWrapper {
    private WorkPlan workPlan;
    private String name;
    private String department;
    private String position;
}
