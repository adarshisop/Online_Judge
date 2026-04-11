package com.adarsh.onlineJudge.service;

import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.entity.TestCase;
import com.adarsh.onlineJudge.repository.TestCaseRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TestCaseService {
private final TestCaseRepository testCaseRepository;

    public TestCase addTestCase(TestCase testCase){
        return testCaseRepository.save(testCase);
    }

    List<TestCase> getTestCasesByProblem(Problem problem){
        return testCaseRepository.findByProblem(problem);
    }

    List<TestCase> getSampleTestCases(Problem problem){
        return testCaseRepository.findByProblemAndIsSample(problem , true);
    }
}
