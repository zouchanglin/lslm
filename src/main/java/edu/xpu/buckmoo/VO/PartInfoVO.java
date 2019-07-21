package edu.xpu.buckmoo.VO;

import lombok.Data;

import java.util.List;

/**
 * 新版本返回的兼职信息数据
 */
@Data
public class PartInfoVO {
    private List<PartInfoOther> partInfoList;
    private Integer pageCount;
}
