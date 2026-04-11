package com.adarsh.onlineJudge.entity;

import com.adarsh.onlineJudge.enums.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Problems")
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String problemId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description ;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty ;


    private int timeLimit;    // milliseconds
    private int memoryLimit;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
