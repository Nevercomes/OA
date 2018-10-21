package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role", schema = "yunlg_oa")
public class Role {
    @Id
    @Column(name = "role_id")
    private long RoleId;
    @Column(name = "name")
    private String roleName;
}
