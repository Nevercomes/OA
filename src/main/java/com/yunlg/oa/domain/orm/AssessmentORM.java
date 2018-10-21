package com.yunlg.oa.domain.orm;

import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.model.User;
import lombok.Data;

@Data
public class AssessmentORM {
    private Assessment assessment;
    private User user;

    public AssessmentORM() {

    }

    public AssessmentORM(Assessment assessment, User user) {
        this.assessment = assessment;
        this.user = user;
    }
}
