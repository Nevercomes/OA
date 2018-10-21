package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "auth", schema = "yunlg_oa")
public class Permission {
    @Id
    @Column(name = "auth_id")
    private long authId;
    @Column(name = "auth_code")
    private String authCode;
    @Column(name = "department")
    private int department;
    @Column(name = "name")
    private String authName;
}
