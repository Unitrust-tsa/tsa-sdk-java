package cn.tsa.sdk.request.ipr;

import cn.tsa.sdk.request.BaseRequest;
import cn.tsa.sdk.response.ipr.IprApplySourceResponse;

/**
 * @author hongwei
 */
public class IprApplySourceRequest extends BaseRequest<IprApplySourceResponse> {

    @Override
    protected String method() {
        return "ipr.opus.apply.source";
    }
}
