package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.model.ipr.IprDownloadModel;
import cn.tsa.sdk.request.BaseRequest;

/**
 * @author hongwei
 */
public class IprDownloadRequest extends BaseRequest<IprDownloadModel> {


    public IprDownloadRequest(String method) {
        super(method, null);
    }

    public IprDownloadRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
