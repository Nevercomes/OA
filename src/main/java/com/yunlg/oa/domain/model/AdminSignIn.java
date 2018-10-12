package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admin_pwd", schema = "yunlg_oa")
public class AdminSignIn {
    @Id
    @Column(name = "admin_id")
    private String adminId;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
}
