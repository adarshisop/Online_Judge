package com.adarsh.onlineJudge.DTO;

import com.adarsh.onlineJudge.enums.Difficulty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {
    //private String problemId;
    private String title;
    private String description ;
    private Difficulty difficulty ;
    private int timeLimit;    // milliseconds
    private int memoryLimit;

}
