package cn.tsa.sdk.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author JenphyJohn
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageableResponse<T> extends BaseResponse {

    /**
     * 页码 默认 1.
     */
    private Integer page;

    /**
     * 每页大小 默认 20.
     */
    private Integer size;

    /**
     * 总条数.
     */
    private Integer total;

    /**
     * 总页数.
     */
    private Integer pages;

    /**
     * 结构数据.
     */
    private List<T> data;

}
