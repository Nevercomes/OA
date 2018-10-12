package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "file_mission", schema = "yunlg_oa")
public class FileMission {
    @Id
    @Column(name = "file_mission_id")
    private long fileMissionId;
    @Column(name = "assess_id")
    private long assessId;
    @Column(name = "name")
    private String name;
    @Column(name = "path")
    private String path;
}
