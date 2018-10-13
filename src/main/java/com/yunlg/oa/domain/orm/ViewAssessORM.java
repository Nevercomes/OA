package com.yunlg.oa.domain.orm;

import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.model.Staff;
import lombok.Data;

@Data
public class ViewAssessORM {
    private Assessment assessment;
    private Staff staff;

    public ViewAssessORM() {
    }

    public ViewAssessORM(Assessment assessment, Staff staff) {
        this.assessment = assessment;
        this.staff = staff;
    }
}
