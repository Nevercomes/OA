package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_role_mapping", schema = "yunlg_oa")
public class UserRoleMapping {
    @Id
    @Column(name = "user_role_id")
    private long userRoleId;
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "user_id")
    private String userId;
}
