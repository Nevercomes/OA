package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "assess_record", schema = "yunlg_oa")
public class AssessRecord {
    @Id
    @Column(name = "assess_record_id")
    private long assessRecordId;
    @Column(name = "year")
    private int year;
    @Column(name = "month")
    private int month;
    @Column(name = "start_time")
    private java.sql.Date startTime;
    @Column(name = "end_time")
    private java.sql.Date endTime;
    @Column(name = "modify_time")
    private java.sql.Date modifyTime;
}
