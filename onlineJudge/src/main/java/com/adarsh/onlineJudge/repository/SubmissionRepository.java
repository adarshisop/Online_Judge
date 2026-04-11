package com.adarsh.onlineJudge.repository;

import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.entity.Submission;
import com.adarsh.onlineJudge.entity.User;
import com.adarsh.onlineJudge.enums.Verdict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission , String> {
    List<Submission> findByUser(User user);
    List<Submission> findByUserAndProblem(User user , Problem Problem);
    List<Submission> findByProblemAndVerdict(Problem problem , Verdict verdict);
}
