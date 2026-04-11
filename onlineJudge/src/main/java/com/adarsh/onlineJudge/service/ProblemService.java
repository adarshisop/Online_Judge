package com.adarsh.onlineJudge.service;

import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.enums.Difficulty;
import com.adarsh.onlineJudge.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;
public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
}

public List<Problem> getAllProblems(){
    return problemRepository.findAll();
}

public List<Problem> getProblemsByDifficulty(Difficulty difficulty){
    return problemRepository.findByDifficulty(difficulty);
}

public Optional<Problem> getProblemById(String problemId){
    return problemRepository.findById(problemId);
}
}
