package cn.tsa.sdk.exception;

/**
 * @author JenphyJohn
 */
public class SdkException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SdkException(String message) {
        super(message);
    }

    public SdkException(String message, Throwable cause) {
        super(message, cause);
    }
}
