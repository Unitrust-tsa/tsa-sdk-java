package cn.tsa.sdk.common;

import lombok.Data;

/**
 * @author JenphyJohn
 */
@Data
public class OpenConfig {

    public static DataNameBuilder DATA_NAME_BUILDER = new TsaDataNameBuilder();

    /**
     * 成功返回码值
     */
    private String successCode = "10000";
    /**
     * 默认版本号
     */
    private String defaultVersion = "1.0";
    /**
     * 字符编码
     */
    private String charset = "UTF-8";
    /**
     * 签名方式
     */
    private String signType = "RSA2";
    /**
     * 格式类型名称
     */
    private String formatType = "json";
    /**
     * 时间戳格式
     */
    private String timestampPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 接口属性名
     */
    private String methodName = "method";
    /**
     * 版本号名称
     */
    private String versionName = "version";
    /**
     * 编码名称
     */
    private String charsetName = "charset";
    /**
     * appKey名称
     */
    private String appKeyName = "app_id";
    /**
     * data名称
     */
    private String dataName = "biz_content";
    /**
     * 时间戳名称
     */
    private String timestampName = "timestamp";
    /**
     * 签名串名称
     */
    private String signName = "sign";
    /**
     * 签名类型名称
     */
    private String signTypeName = "sign_type";
    /**
     * 格式化名称
     */
    private String formatName = "format";
    /**
     * accessToken名称
     */
    private String accessTokenName = "app_auth_token";
    /**
     * 国际化语言
     */
    private String locale = "zh-CN";
    /**
     * 响应code名称
     */
    private String responseCodeName = "code";
    /**
     * 错误响应节点
     */
    private String errorResponseName = "error_response";

    /**
     * 请求超时时间
     */
    private int connectTimeoutSeconds = 60;
    /**
     * http读取超时时间
     */
    private int readTimeoutSeconds = 60;
    /**
     * http写超时时间
     */
    private int writeTimeoutSeconds = 60;

    /**
     * 构建数据节点名称
     */
    private DataNameBuilder dataNameBuilder = DATA_NAME_BUILDER;
}
