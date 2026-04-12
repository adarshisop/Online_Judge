package com.adarsh.onlineJudge.worker;

import com.adarsh.onlineJudge.config.RabbitMQ;
import com.adarsh.onlineJudge.entity.Submission;
import com.adarsh.onlineJudge.enums.Verdict;
import com.adarsh.onlineJudge.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionWorker {
 private  final SubmissionRepository submissionRepository;

 @RabbitListener(queues = RabbitMQ.QUEUE_NAME)
    public void processSubmission(String submissionId){
     System.out.println("Picked up Submission :" + submissionId);
     Submission submission = submissionRepository.findById(submissionId)
             .orElseThrow(() -> new RuntimeException("Submission not found"));

     System.out.println("Processing :" + submission.getLanguage() + " code ");

     submission.setVerdict(Verdict.ACCEPTED);
     submissionRepository.save(submission);
     System.out.println("Verdict updated : " + submission.getVerdict());
 }
}
