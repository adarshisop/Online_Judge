package com.adarsh.onlineJudge.DTO;

import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.entity.User;
import com.adarsh.onlineJudge.enums.Language;
import com.adarsh.onlineJudge.enums.Verdict;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequestDTO {

    private String problemId;
    private String code;
    private Language language;


}
