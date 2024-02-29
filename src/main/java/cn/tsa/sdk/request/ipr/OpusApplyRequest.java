package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.model.ipr.IprApplyModel;
import cn.tsa.sdk.request.BaseRequest;

/**
 * @author hongwei
 */
public class OpusApplyRequest extends BaseRequest<IprApplyModel> {


    public OpusApplyRequest(String method) {
        super(method, null);
    }

    public OpusApplyRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
