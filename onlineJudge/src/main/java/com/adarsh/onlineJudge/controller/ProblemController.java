package com.adarsh.onlineJudge.controller;

import com.adarsh.onlineJudge.DTO.ProblemDTO;
import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.enums.Difficulty;
import com.adarsh.onlineJudge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/create")
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
        Problem newProblem = problemService.createProblem(problem);
        return new ResponseEntity<>(newProblem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblems() {
        List<Problem> problems = problemService.getAllProblems();
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @GetMapping("/{problemId}")
    public ResponseEntity<Problem> getProblemById(@PathVariable String problemId) {
        return problemService.getProblemById(problemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Problem>> getProblemsByDifficulty(@PathVariable Difficulty difficulty) {
        List<Problem> problems = problemService.getProblemsByDifficulty(difficulty);
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }
}