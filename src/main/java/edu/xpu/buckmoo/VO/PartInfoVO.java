package edu.xpu.buckmoo.VO;

import edu.xpu.buckmoo.dataobject.PartInfo;
import lombok.Data;

import java.util.List;

@Data
public class PartInfoVO {
    private List<PartInfo> partInfoList;
    private Integer pageCount;
}
