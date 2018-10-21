package com.yunlg.oa.domain.wrapper;

import lombok.Data;

@Data
public class BatchRegister {
    private String userId;
    private String name;
    private int department;
    private int position;
}
