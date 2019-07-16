package edu.xpu.buckmoo.VO;

import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.dataobject.PartInfo;
import lombok.Data;

import java.util.List;

@Data
public class PartInfoVO {
    private PartCategory partCategory;

    private List<PartInfo> partInfoList;
}
