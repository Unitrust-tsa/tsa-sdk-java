package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.model.ipr.IprDownloadModel;
import cn.tsa.sdk.request.BaseRequest;

/**
 * @author hongwei
 */
public class OpusDownloadRequest extends BaseRequest<IprDownloadModel> {


    public OpusDownloadRequest(String method) {
        super(method, null);
    }

    public OpusDownloadRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
