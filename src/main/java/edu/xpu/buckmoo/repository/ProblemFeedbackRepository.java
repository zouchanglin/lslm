package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.ProblemFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemFeedbackRepository extends JpaRepository<ProblemFeedback, String> {
}
