package cn.tsa.sdk.model.ipr;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hongwei
 */
@Data
@Accessors(chain = true)
public class IprApplySourceModel {

    /**
     * 申请作品hash值 64.
     */
    private String fileHash;

    /**
     * 作品描述，禁止使用特殊符号，不 支持.
     */
    private String opusDescribe;

    /**
     * 申请作品名称.
     */
    private String opusName;

    /**
     * 文件类型 101 文字 111 产品设计图 102 音乐 103 音像 104 美术 105 设计 106 雕塑 107 工程图纸 108 软件 109 商业秘密 110 摄影图片 999 其他.
     */
    private Integer fileType;

    /**
     * 申请人名称.
     */
    private String applyName;

    /**
     * 申请人证件类型.
     */
    private Integer applyIdType;

    /**
     * 申请人证件号码.
     */
    private String applyIdNumber;

    /**
     * 作品发布状态 0 未发布、1 已发布.
     */
    private Integer opusState;

    /**
     * 作品合作伙伴编号.
     */
    private String opusPartnerId;

    /**
     * 作品标签，自定义，多个标签时，采用半角逗号分隔，例如:风景,山水.
     */
    private String opusLabel;

    /**
     * 版权类型.
     */
    private Integer opusStore;

    /**
     * 作品类型.
     */
    private Integer opusType;

    /**
     * 创作类型.
     */
    private Integer opusCreativeType;

    /**
     * 创作性质 101 原创、102 衍生创作.
     */
    private Integer opusCreativeNature;

    /**
     * 申请类型 101 本人、102 代理申请、103 授权平台.
     */
    private Integer applyType;

    /**
     * 申请人国籍 中国CN.
     */
    private String applyNationality;

    /**
     * 申请人联系电话.
     */
    private String applyPhone;

    /**
     * 申请人电子邮箱.
     */
    private String applyMail;

    /**
     * 申请人联系地址.
     */
    private String applyAddress;

    /**
     * 申请人的紧急联系人.
     */
    private String applyEmergencyName;

    /**
     * 申请人紧急联系人联系电话.
     */
    private String applyEmergencyPhone;

    /**
     * 说明.
     */
    private String remark;

    /**
     * 接口回调地址.
     */
    private String callbackUrl;

    /**
     * 原文件
     */
    private String fileBase64;

    /**
     * 101 个人、102 企业或者组织.
     */
    private Integer applyUserType;

    /**
     * 申请固化类别  0 普通申请固化 1接口申请固化.
     */
    private Integer opusApplyType;

    /**
     * 1 中文 2英文 3双语
     */
    private Integer templateType;

    /**
     * 是否上传原件 0 不上传 1 上传
     */
    private Integer sourceFileUploadType;

    /**
     * 文件md5
     */
    private String md5Hash;

    /**
     * 第三方用户扩展信息
     */
    private String externalUserInfo;
}
