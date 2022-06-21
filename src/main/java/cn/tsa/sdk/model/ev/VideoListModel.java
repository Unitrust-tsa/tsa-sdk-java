package cn.tsa.sdk.model.ev;

import cn.tsa.sdk.model.common.Pageable;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Description:
 *
 * @author JenphyJohn
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VideoListModel extends Pageable {

    @JSONField(name = "status")
    private Integer status;
}
