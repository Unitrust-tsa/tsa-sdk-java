package cn.tsa.sdk.sign;


/**
 * @author JenphyJohn
 */
public class TsaSignException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public TsaSignException() {
        super();
    }

    public TsaSignException(String message, Throwable cause) {
        super(message, cause);
    }

    public TsaSignException(String message) {
        super(message);
    }

    public TsaSignException(Throwable cause) {
        super(cause);
    }

    public TsaSignException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}