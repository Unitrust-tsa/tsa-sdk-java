package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.request.BaseRequest;
import cn.tsa.sdk.response.PageableResponse;
import cn.tsa.sdk.response.ipr.IprSearchResponse;

/**
 * @author hongwei
 */
public class IprSearchRequest extends BaseRequest<PageableResponse<IprSearchResponse>> {

    @Override
    protected String method() {
        return "ipr.opus.search";
    }
}
