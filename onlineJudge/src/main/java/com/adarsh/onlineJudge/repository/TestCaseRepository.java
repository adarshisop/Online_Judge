package com.adarsh.onlineJudge.repository;

import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase , String> {

    // find all the testcases of a problem
    List<TestCase> findByProblem(Problem problem);
    List<TestCase> findByProblemAndIsSample(Problem problem , Boolean isSample);


}
