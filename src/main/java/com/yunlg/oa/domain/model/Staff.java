package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_info", schema = "yunlg_oa")
public class Staff {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private int department;
    @Column(name = "position")
    private int position;
}
