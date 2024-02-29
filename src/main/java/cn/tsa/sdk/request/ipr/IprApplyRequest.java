package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.model.ipr.IprApplyModel;
import cn.tsa.sdk.request.BaseRequest;

/**
 * @author hongwei
 */
public class IprApplyRequest extends BaseRequest<IprApplyModel> {


    public IprApplyRequest(String method) {
        super(method, null);
    }

    public IprApplyRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
