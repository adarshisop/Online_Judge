package com.adarsh.onlineJudge.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Test_cases")
public class TestCase {
//    testCaseId    PK
//    problemId     FK → problems
//            inputData
//    expectedOutput
//    isSample      (show to user or hidden)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String testCaseId;

    @ManyToOne
    @JoinColumn(name = "problem_id" , nullable = false)
    private Problem problem;

    @Column(columnDefinition = "TEXT")
    private  String inputData;

    @Column(columnDefinition = "TEXT")
    private String expectedOutput;

    private boolean isSample;

}
