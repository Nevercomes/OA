package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "assessment", schema = "yunlg_oa")
public class Assessment {
    @Id
    @Column(name = "assess_id")
    private long assessId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "work_regular")
    private String workRegular;
    @Column(name = "work_outPlan")
    private String workOutPlan;
    @Column(name = "work_other")
    private String workOther;
    @Column(name = "work_expanse")
    private String workExpanse;
    @Column(name = "work_planSimple")
    private String workPlanSimple;
    @Column(name = "assess_head_eva")
    private String assessHeadEva;
    @Column(name = "assess_head_score")
    private float assessHeadScore;
    @Column(name = "assess_director_eva")
    private String assessDirectorEva;
    @Column(name = "assess_director_score")
    private float assessDirectorScore;
    @Column(name = "remark")
    private String remark;
    @Column(name = "month")
    private int month;
    @Column(name = "work_modify_time")
    private java.sql.Time workModifyTime;
    @Column(name = "assess_modify_time")
    private java.sql.Time assessModifyTime;

}
