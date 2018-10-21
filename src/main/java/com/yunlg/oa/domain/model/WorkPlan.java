package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "work_plan", schema = "yunlg_oa")
public class WorkPlan {
    @Id
    @Column(name = "plan_id")
    private long planId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "month")
    private int month;
    @Column(name = "content")
    private String content;
    @Column(name = "modify_time")
    private java.sql.Date modifyTime;
}
