package cn.tsa.sdk.response;

import cn.tsa.sdk.util.StringUtils;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回对象基类，后续返回对象都要继承这个类
 *
 * @author JenphyJohn
 */
@Setter
@Getter
public abstract class BaseResponse {

    @JSONField(name = "request_id")
    private String requestId;
    private String code;
    private String msg;
    @JSONField(name = "sub_code")
    private String subCode;
    @JSONField(name = "sub_msg")
    private String subMsg;
    @JSONField(serialize = false)
    private String body;

    @JSONField(serialize = false)
    public boolean isSuccess() {
        return StringUtils.isEmpty(subCode);
    }


}
