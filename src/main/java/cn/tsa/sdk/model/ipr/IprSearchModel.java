package cn.tsa.sdk.model.ipr;

import cn.tsa.sdk.model.common.Pageable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author hongwei
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class IprSearchModel extends Pageable {

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
