package cn.tsa.sdk.model.ipr;

import cn.tsa.sdk.model.ipr.dto.IprSearchDTO;
import cn.tsa.sdk.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author hongwei
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class IprSearchModel extends BaseResponse {

    private List<IprSearchDTO> data;
}
