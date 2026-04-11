package com.adarsh.onlineJudge.controller;

import com.adarsh.onlineJudge.DTO.SubmissionRequestDTO;
import com.adarsh.onlineJudge.entity.Submission;
import com.adarsh.onlineJudge.entity.User;
import com.adarsh.onlineJudge.repository.UserRepository;
import com.adarsh.onlineJudge.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;
    private final UserRepository userRepository;
    @PostMapping("/submit")
    public ResponseEntity<Submission> submit(@RequestBody SubmissionRequestDTO request,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Submission submission = submissionService.createSubmission(request, user);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }
}
