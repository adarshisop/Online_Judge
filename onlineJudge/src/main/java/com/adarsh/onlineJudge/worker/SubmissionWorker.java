package com.adarsh.onlineJudge.worker;

import com.adarsh.onlineJudge.config.RabbitMQ;
import com.adarsh.onlineJudge.entity.Submission;
import com.adarsh.onlineJudge.entity.TestCase;
import com.adarsh.onlineJudge.enums.Verdict;
import com.adarsh.onlineJudge.repository.SubmissionRepository;
import com.adarsh.onlineJudge.repository.TestCaseRepository;
import com.adarsh.onlineJudge.service.DockerExcutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionWorker {

 private  final SubmissionRepository submissionRepository;
 private final TestCaseRepository testCaseRepository;
 private final DockerExcutionService dockerExcutionService;

 @RabbitListener(queues = RabbitMQ.QUEUE_NAME)
    public void processSubmission(String submissionId){
     System.out.println("Picked up Submission :" + submissionId);
     Submission submission = submissionRepository.findById(submissionId)
             .orElseThrow(() -> new RuntimeException("Submission not found"));

  List<TestCase> testCases = testCaseRepository.findByProblem(submission.getProblem());

  if(testCases.isEmpty()){
   submission.setVerdict(Verdict.ACCEPTED);
   submissionRepository.save(submission);
   return;
  }

  Verdict finalVerdict = Verdict.ACCEPTED;

  for(TestCase testCase : testCases){
   String output = dockerExcutionService.executeCode(
           submission.getCode(),
           submission.getLanguage().toString(),
           testCase.getInputData()
   );
   System.out.println("Expected: " + testCase.getExpectedOutput());
   System.out.println("Got: " + output);

   if (output.equals("TLE")) {
    finalVerdict = Verdict.TIME_LIMIT_EXCEEDED;
    break;
   }

   if (!output.trim().equals(testCase.getExpectedOutput().trim())) {
    finalVerdict = Verdict.WRONG_ANSWER;
    break;
   }
  }

     submission.setVerdict(finalVerdict);
    submissionRepository.save(submission);
     System.out.println("Verdict updated : " + submission.getVerdict());
 }
}
