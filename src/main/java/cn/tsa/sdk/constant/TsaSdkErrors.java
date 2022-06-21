package cn.tsa.sdk.constant;


import cn.tsa.sdk.response.ErrorResponse;

/**
 * @author JenphyJohn
 */
public enum TsaSdkErrors {
    /**
     * 网络错误
     */
    HTTP_ERROR("20000", "网络错误"),
    /**
     * 验证返回sign错误
     */
    CHECK_RESPONSE_SIGN_ERROR("20001", "验证服务端sign出错");

    TsaSdkErrors(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.subCode = code;
        this.subMsg = msg;
    }

    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(code);
        errorResponse.setSubCode(subCode);
        errorResponse.setSubMsg(subMsg);
        errorResponse.setMsg(msg);
        return errorResponse;
    }

    private String code;
    private String msg;
    private String subCode;
    private String subMsg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

}
