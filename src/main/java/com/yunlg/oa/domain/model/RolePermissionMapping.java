package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role_auth_mapping", schema = "yunlg_oa")
public class RolePermissionMapping {
    @Id
    @Column(name = "role_auth_id")
    private long roleAuthId;
    @Column(name = "auth_code")
    private String authCode;
    @Column(name = "role_id")
    private long roleId;
}
