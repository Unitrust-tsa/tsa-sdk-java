package cn.tsa.sdk.request;


import cn.tsa.sdk.response.CommonResponse;

/**
 * @author JenphyJohn
 */
public class CommonRequest extends BaseRequest<CommonResponse> {

    public CommonRequest(String method) {
        super(method, null);
    }

    public CommonRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
