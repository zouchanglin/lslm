package edu.xpu.buckmoo.controller;

import edu.xpu.buckmoo.dataobject.ProblemFeedback;
import edu.xpu.buckmoo.repository.ProblemFeedbackRepository;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 问题反馈的Controller
 */
@RestController
@Slf4j
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemFeedbackRepository problemFeedbackRepository;

    public ProblemController(ProblemFeedbackRepository problemFeedbackRepository) {
        this.problemFeedbackRepository = problemFeedbackRepository;
    }

    @PostMapping("/feedback")
    public String feedback(@CookieValue(value = "problem_count", required = false) String problemCount,
                            @RequestParam("content") String content,
                           @RequestParam("contact") String contact,
                           HttpServletResponse httpServletResponse){
        //cookie先到位
        if(problemCount != null){
            try{
                if(Integer.parseInt(problemCount) > 10){
                    return JsonUtil.toJson(ResultVOUtil.error(1, "今日留言已达上限"));
                }else{
                    Cookie cookie = new Cookie("problem_count", String.valueOf(Integer.parseInt(problemCount) + 1));
                    cookie.setMaxAge(3600 * 24);
                    httpServletResponse.addCookie(cookie);
                }
            }catch (Exception e){
                log.error("数字解析错误...");
            }
        }else{
            Cookie cookie = new Cookie("problem_count", "1");
            cookie.setMaxAge(3600 * 24);
            httpServletResponse.addCookie(cookie);
        }

        ProblemFeedback problemFeedback = new ProblemFeedback();
        problemFeedback.setProblemContent(content);
        problemFeedback.setContactWay(contact);
        problemFeedback.setProblemId(KeyUtil.genUniqueKey());
        problemFeedback.setProblemDealwith(0);

        ProblemFeedback saveFeedback = problemFeedbackRepository.save(problemFeedback);
        log.info("[ProblemController] saveFeedback={}", saveFeedback);
        return JsonUtil.toJson(ResultVOUtil.success(saveFeedback));
    }

}
