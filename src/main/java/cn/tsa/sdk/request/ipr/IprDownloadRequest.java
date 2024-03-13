package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.request.BaseRequest;
import cn.tsa.sdk.response.ipr.IprDownloadResponse;

/**
 * @author hongwei
 */
public class IprDownloadRequest extends BaseRequest<IprDownloadResponse> {


    @Override
    protected String method() {
        return "ipr.opus.download";
    }
}
