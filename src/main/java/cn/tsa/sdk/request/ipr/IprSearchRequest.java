package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.model.ipr.IprSearchModel;
import cn.tsa.sdk.request.BaseRequest;

/**
 * @author hongwei
 */
public class IprSearchRequest extends BaseRequest<IprSearchModel> {


    public IprSearchRequest(String method) {
        super(method, null);
    }

    public IprSearchRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
