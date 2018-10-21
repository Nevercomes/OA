package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "assess_result", schema = "yunlg_oa")
public class AssessResult {
    @Id
    @Column(name = "result_id")
    private long resultId;
    @Column(name = "department")
    private int department;
    @Column(name = "month")
    private int month;
    @Column(name = "modify_time")
    private java.sql.Date modifyTime;
}
