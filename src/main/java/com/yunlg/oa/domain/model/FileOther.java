package com.yunlg.oa.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "file_other", schema = "yunlg_oa")
public class FileOther {
    @Id
    @Column(name = "file_other_id")
    private long fileOtherId;
    @Column(name = "assess_id")
    private long assessId;
    @Column(name = "name")
    private String name;
    @Column(name = "path")
    private String path;
}
