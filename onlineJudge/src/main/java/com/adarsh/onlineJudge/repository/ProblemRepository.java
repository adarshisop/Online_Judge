package com.adarsh.onlineJudge.repository;

import com.adarsh.onlineJudge.entity.Problem;
import com.adarsh.onlineJudge.enums.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, String> {

    List<Problem> findByDifficulty(Difficulty difficulty);

}
