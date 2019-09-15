package edu.xpu.buckmoo.VO;

import lombok.Data;

import java.util.List;

@Data
public class CompanyInfoVOStruct {
    private Integer pageIndex;
    private Integer pageCount;
    private List<CompanyInfoVO> listPass;
}
