package edu.xpu.buckmoo.VO;

import edu.xpu.buckmoo.dataobject.ProblemFeedback;
import lombok.Data;

import java.util.List;
@Data
public class ProblemVOStruct {
    private List<ProblemFeedback> list;
    private Integer pageCount;
    private Integer pageIndex;
}
