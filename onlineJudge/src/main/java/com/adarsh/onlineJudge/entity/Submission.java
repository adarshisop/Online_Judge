package com.adarsh.onlineJudge.entity;

import com.adarsh.onlineJudge.enums.Language;
import com.adarsh.onlineJudge.enums.Verdict;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.boot.internal.Target;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String submissionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Enumerated(EnumType.STRING)
    private Language language;  // JAVA, PYTHON, CPP

    @Enumerated(EnumType.STRING)
    private Verdict verdict;    // PENDING, ACCEPTED, WRONG_ANSWER, TLE

    private long executionTime;  // ms
    private long memoryUsed;     // MB

    @CreationTimestamp
    private LocalDateTime submittedAt;
}
