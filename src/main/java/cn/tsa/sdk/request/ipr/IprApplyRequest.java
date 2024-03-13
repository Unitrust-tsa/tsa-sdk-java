package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.request.BaseRequest;
import cn.tsa.sdk.response.ipr.IprApplyResponse;

/**
 * @author hongwei
 */
public class IprApplyRequest extends BaseRequest<IprApplyResponse> {

    @Override
    protected String method() {
        return "ipr.opus.apply";
    }
}
