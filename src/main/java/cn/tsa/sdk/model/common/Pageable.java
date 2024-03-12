package cn.tsa.sdk.model.common;

/**
 * Description:
 *
 * @author JenphyJohn
 */
public class Pageable {

    /**
     * 页码 默认 1.
     */
    private Integer page;

    /**
     * 每页大小 默认 20.
     */
    private Integer size;

    private Integer total;

    private Integer pages;

    public Integer getSize() {
        if (size == null || size > 100) {
            return 20;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        if (page == null) {
            return 1;
        }
        return page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
