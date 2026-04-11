package com.adarsh.onlineJudge.service;

import com.adarsh.onlineJudge.DTO.SubmissionRequestDTO;
import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.entity.Submission;
import com.adarsh.onlineJudge.entity.User;
import com.adarsh.onlineJudge.enums.Verdict;
import com.adarsh.onlineJudge.repository.ProblemRepository;
import com.adarsh.onlineJudge.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {
//    SubmissionService
//├── createSubmission(Submission submission)
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    public Submission createSubmission(SubmissionRequestDTO request , User user){

        Submission submission = new Submission();
        submission.setUser(user);
        submission.setLanguage(request.getLanguage());
        submission.setProblem(problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> new RuntimeException("No")));
         submission.setCode(request.getCode());
         submission.setVerdict(Verdict.PENDING);
         return submissionRepository.save(submission);

    }
//└── getSubmissionsByUser(User user)
public List<Submission> getSubmissionByUser(User user){
        return submissionRepository.findByUser(user);
}
//└── getSubmissionsByUserAndProblem(User user, Problem problem)
public List<Submission> getSubmissionByUserAndProblem(User user , Problem problem){
        return submissionRepository.findByUserAndProblem(user , problem);
}

public List<Submission> getSubmissionByProblemAndVerdict(Problem problem , Verdict verdict){
        return submissionRepository.findByProblemAndVerdict(problem , verdict);
}


}
