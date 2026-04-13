package com.adarsh.onlineJudge.controller;

import com.adarsh.onlineJudge.DTO.TestCaseRequestDTO;
import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.entity.TestCase;
import com.adarsh.onlineJudge.repository.ProblemRepository;
import com.adarsh.onlineJudge.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testcases")
@RequiredArgsConstructor
public class TestCaseController {
    private final TestCaseService testCaseService;
    private final ProblemRepository problemRepository;

    @PostMapping("/add")
    public ResponseEntity<TestCase> addTestCase(@RequestBody TestCaseRequestDTO request){
        Problem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        TestCase testCase = new TestCase();
        testCase.setProblem(problem);
        testCase.setInputData(request.getInputData());
        testCase.setExpectedOutput(request.getExpectedOutput());
        testCase.setSample(request.isSample());

        return new ResponseEntity<>(testCaseService.addTestCase(testCase), HttpStatus.CREATED);
    }
}
