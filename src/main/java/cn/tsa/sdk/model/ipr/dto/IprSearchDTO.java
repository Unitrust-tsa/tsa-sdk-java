package cn.tsa.sdk.model.ipr.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hongwei
 */
@Data
@Accessors(chain = true)
public class IprSearchDTO {

    /**
     * 唯一键(申请作品唯一标识:serialNo)
     */
    private String oid;

    /**
     * 申请作品hash值 64.
     */
    private String fileHash;


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
     * 申请作品名称.
     */
    private String opusName;

    /**
     * 注册时间
     */
    private Long operatedAt;

    /**
     * 申请人名称.
     */
    private String applyName;

    /**
     * pdf证书编号
     */
    private String pdfSerialNo;


}
