package cn.tsa.sdk.model.ipr;

import cn.tsa.sdk.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author hongwei
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class IprApplyModel extends BaseResponse {

    /**
     * 唯一键(申请作品唯一标识:serialNo)
     */
    private String oid;

    /**
     * 申请作品名称
     */
    private String opusName;

    /**
     * 申请作品hash值 64.
     */
    private String fileHash;

    /**
     * 固化时间
     */
    private Long operatedAt;

    /**
     * pdf外网下载地址
     */
    private String pdfUrl;

    /**
     * tsa外网下载地址
     */
    private String tsaUrl;

    /**
     * 原文件外网下载地址
     */
    private String sourceFileUrl;

    /**
     * 原文件上传地址
     */
    private String uploadSourceFileUrl;

    /**
     * pdf证书编号
     */
    private String pdfSerialNo;
}
