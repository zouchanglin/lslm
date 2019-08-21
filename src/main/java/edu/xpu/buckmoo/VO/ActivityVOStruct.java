package edu.xpu.buckmoo.VO;

import lombok.Data;

import java.util.List;

@Data
public class ActivityVOStruct {
    private List<ActivityInfoVO> list;
    private Integer count;
    private Integer pageCount;
    private Integer currentPage;
}
