package com.yunlg.oa.domain.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_pwd", schema = "yunlg_oa")
public class AdminSignIn {

    @Id
    @Column(name = "admin_id")
    private String adminId;
    @Column(name = "numbering")
    private String numbering;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
}
