package edu.xpu.buckmoo.repository;


import edu.xpu.buckmoo.dataobject.ProblemFeedback;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProblemFeedbackRepositoryTest {

    @Autowired
    private ProblemFeedbackRepository problemFeedbackRepository;

    @Test
    public void save(){
        ProblemFeedback problemFeedback = new ProblemFeedback();
        problemFeedback.setProblemId(KeyUtil.genUniqueKey());
        problemFeedback.setProblemContent("内容");

        ProblemFeedback feedback = problemFeedbackRepository.save(problemFeedback);
        log.info("[ProblemFeedbackRepositoryTest] feedback={}", feedback);
        assertNotNull(feedback);
    }
}