package cn.tsa.sdk;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hongwei
 */
@Data
@Accessors(chain = true)
public class IprSearchParam {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 显示数量
     */
    private Integer size;

    /**
     * 申请时间开始
     */
    private Long startAt;

    /**
     * 申请时间结束
     */
    private Long endAt;

    /**
     * hash
     */
    private String fileHash;

    /**
     * 申请方式
     */
    private String opusApplyType;


}
