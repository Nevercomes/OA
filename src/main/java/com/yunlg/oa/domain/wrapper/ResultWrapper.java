package com.yunlg.oa.domain.wrapper;

import lombok.Data;

@Data
public class ResultWrapper {
    private String department;
    private String staffName;
    private String position;
    private int score;
    private int rank;
}
