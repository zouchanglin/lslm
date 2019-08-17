package edu.xpu.buckmoo.VO;

import edu.xpu.buckmoo.dataobject.order.PartInfo;
import lombok.Data;

import java.util.List;

/**
 * 旧版兼职信息返回数据
 */
@Data
public class PartInfoOldVO {
    private List<PartInfo> partInfoList;
    private Integer pageCount;
}
