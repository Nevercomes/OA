package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admin", schema = "yunlg_oa")
public class Admin {
    @Id
    @Column(name = "admin_id")
    private String adminId;
    @Column(name = "numbering")
    private String numbering;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private int department;
    @Column(name = "position")
    private int position;
}
