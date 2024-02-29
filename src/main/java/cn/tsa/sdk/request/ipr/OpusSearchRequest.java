package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.model.ipr.IprSearchModel;
import cn.tsa.sdk.request.BaseRequest;

/**
 * @author hongwei
 */
public class OpusSearchRequest extends BaseRequest<IprSearchModel> {


    public OpusSearchRequest(String method) {
        super(method, null);
    }

    public OpusSearchRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
