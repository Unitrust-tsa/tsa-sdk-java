package cn.tsa.sdk.client;

import cn.tsa.sdk.common.DataNameBuilder;
import cn.tsa.sdk.common.OpenConfig;
import cn.tsa.sdk.common.RequestForm;
import cn.tsa.sdk.constant.TsaSdkConstants;
import cn.tsa.sdk.constant.TsaSdkErrors;
import cn.tsa.sdk.exception.SdkException;
import cn.tsa.sdk.request.BaseRequest;
import cn.tsa.sdk.response.BaseResponse;
import cn.tsa.sdk.response.ErrorResponse;
import cn.tsa.sdk.sign.TsaSignException;
import cn.tsa.sdk.sign.TsaSignature;
import cn.tsa.sdk.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collections;
import java.util.Map;

/**
 * 请求客户端，申明一个即可
 *
 * @author JenphyJohn
 */
public class OpenClient {
    private static final Log log = LogFactory.getLog(OpenClient.class);

    /**
     * 默认配置
     */
    private static final OpenConfig DEFAULT_CONFIG = new OpenConfig();

    /**
     * 接口请求url
     */
    private final String url;

    /**
     * 平台提供的appId
     */
    private final String appId;

    /**
     * 开放平台提供的私钥
     */
    private final String privateKey;

    /**
     * 开放平台提供的公钥
     */
    private String publicKeyPlatform;

    /**
     * 配置项
     */
    private final OpenConfig openConfig;

    /**
     * 请求对象
     */
    private final OpenRequest openRequest;

    /**
     * 节点处理
     */
    private final DataNameBuilder dataNameBuilder;

    /**
     * 构建请求客户端
     *
     * @param url           接口url
     * @param appId         平台分配的appId
     * @param privateKeyIsv 平台分配的私钥
     */
    public OpenClient(String url, String appId, String privateKeyIsv) {
        this(url, appId, privateKeyIsv, DEFAULT_CONFIG);
    }

    /**
     * 构建请求客户端
     *
     * @param url               接口url
     * @param appId             平台分配的appId
     * @param privateKeyIsv     平台分配的私钥
     * @param publicKeyPlatform 平台分配的公钥
     */
    public OpenClient(String url, String appId, String privateKeyIsv, String publicKeyPlatform) {
        this(url, appId, privateKeyIsv);
        this.publicKeyPlatform = publicKeyPlatform;
    }

    /**
     * 构建请求客户端
     *
     * @param url           接口url
     * @param appId         平台分配的appId
     * @param privateKeyIsv 平台分配的私钥
     * @param openConfig    配置项
     */
    public OpenClient(String url, String appId, String privateKeyIsv, OpenConfig openConfig) {
        if (openConfig == null) {
            throw new IllegalArgumentException("openConfig不能为null");
        }
        this.url = url;
        this.appId = appId;
        this.privateKey = privateKeyIsv;
        this.openConfig = openConfig;

        this.openRequest = new OpenRequest(openConfig);
        this.dataNameBuilder = openConfig.getDataNameBuilder();
    }

    /**
     * 构建请求客户端
     *
     * @param url               接口url
     * @param appId             平台分配的appId
     * @param privateKeyIsv     平台分配的私钥
     * @param publicKeyPlatform 平台分配的公钥
     * @param openConfig        配置项
     */
    public OpenClient(String url, String appId, String privateKeyIsv, String publicKeyPlatform, OpenConfig openConfig) {
        this(url, appId, privateKeyIsv, openConfig);
        this.publicKeyPlatform = publicKeyPlatform;
    }

    /**
     * 请求接口
     *
     * @param request 请求对象
     * @param <T>     返回对应的Response
     * @return 返回Response
     */
    public <T extends BaseResponse> T execute(BaseRequest<T> request) {
        return this.execute(request, null);
    }

    /**
     * 请求接口
     *
     * @param request     请求对象
     * @param accessToken jwt
     * @param <T>         返回对应的Response
     * @return 返回Response
     */
    public <T extends BaseResponse> T execute(BaseRequest<T> request, String accessToken) {
        RequestForm requestForm = request.createRequestForm(this.openConfig);
        // 表单数据
        Map<String, String> form = requestForm.getForm();
        if (accessToken != null) {
            form.put(this.openConfig.getAccessTokenName(), accessToken);
        }
        form.put(this.openConfig.getAppKeyName(), this.appId);

        String content = TsaSignature.getSignContent(form);
        String sign;
        try {
            sign = TsaSignature.rsaSign(content, privateKey, openConfig.getCharset(), openConfig.getSignType());
        } catch (TsaSignException e) {
            throw new SdkException("构建签名错误", e);
        }

        form.put(this.openConfig.getSignName(), sign);

        String resp = doExecute(this.url, requestForm, Collections.emptyMap());
        if (log.isDebugEnabled()) {
            log.debug("----------- 请求信息 -----------"
                    + "\n请求参数：" + TsaSignature.getSignContent(form)
                    + "\n待签名内容：" + content
                    + "\n签名(sign)：" + sign
                    + "\n----------- 返回结果 -----------"
                    + "\n" + resp
            );
        }
        return this.parseResponse(resp, request);
    }

    protected String doExecute(String url, RequestForm requestForm, Map<String, String> header) {
        return openRequest.request(url, requestForm, header);
    }

    /**
     * 解析返回结果
     *
     * @param resp    返回结果
     * @param request 请求对象
     * @param <T>     返回结果
     * @return 返回对于的Response对象
     */
    protected <T extends BaseResponse> T parseResponse(String resp, BaseRequest<T> request) {
        String method = request.getMethod();
        String rootNodeName = dataNameBuilder.build(method);
        JSONObject jsonObject = JSON.parseObject(resp, Feature.OrderedField);
        String errorResponseName = this.openConfig.getErrorResponseName();
        boolean errorResponse = jsonObject.containsKey(errorResponseName);
        if (errorResponse) {
            rootNodeName = errorResponseName;
        }
        JSONObject data = jsonObject.getJSONObject(rootNodeName);
        String sign = jsonObject.getString(openConfig.getSignName());
        // 是否要验证返回的sign
        if (StringUtils.areNotEmpty(sign, publicKeyPlatform)) {
            String signContent = buildBizJson(rootNodeName, resp);
            if (!this.checkResponseSign(signContent, sign, publicKeyPlatform)) {
                ErrorResponse error = TsaSdkErrors.CHECK_RESPONSE_SIGN_ERROR.getErrorResponse();
                data = JSON.parseObject(JSON.toJSONString(error));
            }
        }
        T t = data.toJavaObject(request.getResponseClass());
        t.setBody(jsonObject.getString(rootNodeName));
        return t;
    }

    /**
     * 构建业务json内容。
     * 假设返回的结果是：<br>
     * {"tsa_response":{"msg":"Success","code":"10000","name":"名称","id":1},"sign":"xxx"}
     * 将解析得到：<br>
     * {"msg":"Success","code":"10000","name":"名称","id":1}
     *
     * @param rootNodeName 根节点名称
     * @param body         返回内容
     * @return 返回业务json
     */
    protected String buildBizJson(String rootNodeName, String body) {
        int indexOfRootNode = body.indexOf(rootNodeName);
        if (indexOfRootNode < 0) {
            rootNodeName = TsaSdkConstants.ERROR_RESPONSE_KEY;
            indexOfRootNode = body.indexOf(rootNodeName);
        }
        String result = null;
        if (indexOfRootNode > 0) {
            result = buildJsonNodeData(body, rootNodeName, indexOfRootNode);
        }
        return result;
    }

    /**
     * 获取业务结果，如下结果：<br>
     * {"tsa_response":{"msg":"Success","code":"10000","name":"名称","id":1},"sign":"xxx"}
     * 将返回：<br>
     * {"msg":"Success","code":"10000","name":"名称","id":1}
     *
     * @param body            返回内容
     * @param rootNodeName    根节点名称
     * @param indexOfRootNode 根节点名称位置
     * @return 返回业务json内容
     */
    protected String buildJsonNodeData(String body, String rootNodeName, int indexOfRootNode) {
        /*
          得到起始索引位置。{"alipay_story_get_response":{"msg":"Success","code":"10000","name":"海底小纵队","id":1},"sign":"xxx"}
          得到第二个`{`索引位置
         */
        int signDataStartIndex = indexOfRootNode + rootNodeName.length() + 2;
        // 然后这里计算出"sign"字符串所在位置
        int indexOfSign = body.indexOf("\"" + openConfig.getSignName() + "\"");
        if (indexOfSign < 0) {
            return null;
        }
        int length = indexOfSign - 1;
        // 根据起始位置和长度，截取出json：{"msg":"Success","code":"10000","name":"海底小纵队","id":1}
        return body.substring(signDataStartIndex, length);
    }

    /**
     * 校验返回结果中的sign
     *
     * @param signContent       校验内容
     * @param sign              sign
     * @param publicKeyPlatform 平台公钥
     * @return true：正确
     */
    protected boolean checkResponseSign(String signContent, String sign, String publicKeyPlatform) {
        try {
            String charset = this.openConfig.getCharset();
            String signType = this.openConfig.getSignType();
            return TsaSignature.rsaCheck(signContent, sign, publicKeyPlatform, charset, signType);
        } catch (TsaSignException e) {
            log.error("验证服务端sign出错，signContent：" + signContent, e);
            return false;
        }
    }


}
