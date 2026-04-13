package com.adarsh.onlineJudge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TestCaseRequestDTO {
    private String problemId;
    private String inputData;
    private String expectedOutput;
    private boolean isSample;
}
