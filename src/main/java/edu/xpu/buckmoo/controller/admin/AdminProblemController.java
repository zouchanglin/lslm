package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.VO.ProblemVOStruct;
import edu.xpu.buckmoo.dataobject.ProblemFeedback;
import edu.xpu.buckmoo.repository.ProblemFeedbackRepository;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/admin/problem")
public class AdminProblemController {
    @Autowired
    private ProblemFeedbackRepository problemFeedbackRepository;

    @GetMapping("/show")
    public String show(@RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                       @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                       HttpSession httpSession){

        String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
        if(BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin")) return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));


        Page<ProblemFeedback> feedbackRepositoryAll = problemFeedbackRepository.findAll(PageRequest.of(pageIndex, pageSize));
        ProblemVOStruct problemVOStruct = new ProblemVOStruct();
        problemVOStruct.setList(feedbackRepositoryAll.getContent());
        problemVOStruct.setPageCount(feedbackRepositoryAll.getTotalPages());
        problemVOStruct.setPageIndex(pageIndex);

        return JsonUtil.toJson(ResultVOUtil.success(problemVOStruct));
    }

    /**
     * 标记为已经处理
     * @param problemId
     * @return
     */
    @GetMapping ("/dealWith")
    public String dealWith(String problemId, HttpSession httpSession){
        String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
        if(BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin")) return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));


        Optional<ProblemFeedback> byId = problemFeedbackRepository.findById(problemId);
        if(byId.isPresent()){
            ProblemFeedback problemFeedback = byId.get();
            problemFeedback.setProblemDealwith(1);
            return JsonUtil.toJson(ResultVOUtil.success(problemFeedbackRepository.save(problemFeedback)));
        }else{
            return JsonUtil.toJson(ResultVOUtil.error(1, "未能找到此条信息"));
        }
    }
}
