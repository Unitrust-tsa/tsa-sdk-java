package cn.tsa.sdk.common;

import cn.tsa.sdk.constant.RequestMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 请求form
 *
 * @author JenphyJohn
 */
@Getter
@Setter
public class RequestForm {

    /**
     * 请求表单内容
     */
    private Map<String, String> form;
    /**
     * 上传文件
     */
    private List<UploadFile> files;

    private String charset;

    private RequestMethod requestMethod = RequestMethod.POST;

    public RequestForm(Map<String, String> m) {
        this.form = m;
    }
}
