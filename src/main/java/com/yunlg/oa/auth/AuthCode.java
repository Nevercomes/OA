package com.yunlg.oa.auth;

public enum AuthCode {
    Allow("00000", "00000", "ALL", "允许访问"),

    AU0000("0000", "AU0000", "User_All"),

    AA1000("1000", "AA1000", "AA_Batch_Register"),
    AA1001("1001", "AA1001", "AA_View_Result"),
    AA1002("1002", "AA1002", "AA_Public_Result"),
    AA1003("1003", "AA1003", "AA_Eva_Assessment"),
    AA1004("1004", "AA1004", "AA_Submit_Eva"),
    AA1005("1005", "AA1005", "AA_View_Plan"),
    AA1006("1006", "AA1006", "AA_Reset"),

    AA2000("2000", "AA2000", "AA_Admin_Register"),
    AA2001("2001", "AA2001", "AA_Set_Time"),
    AA2002("2002", "AA2002", "AA_Export_Assess_Info"),
    AA2003("2003", "AA2003", "AA_Export_Assess_Result");

    /**权限标识 */
    private String authId;
    /**权限编码 */
    private
    String authCode;
    /**权限名称 */
    private String authName;
    /**权限描述 */
    private String authDesc;

    AuthCode(String authId, String authCode, String authName) {
        this.authId = authId;
        this.authCode = authCode;
        this.authName = authName;
    }

    AuthCode(String authId, String authCode, String authName, String authDesc) {
        this.authId = authId;
        this.authCode = authCode;
        this.authName = authName;
        this.authDesc = authDesc;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }
}

//    AA2000("2000", "AA2000", "AA_2_Batch_Register"),
//    AA2001("2001", "AA2001", "AA_2_View_Result"),
//    AA2002("2002", "AA2002", "AA_2_Public_Result"),
//    AA2003("2003", "AA2003", "AA_2_View_Eva"),
//    AA2004("2004", "AA2004", "AA_2_Submit_Eva"),
//    AA2005("2005", "AA2005", "AA_2_View_Plan");
