package com.adarsh.onlineJudge.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

@Service
public class DockerExcutionService {
    public String executeCode(String code , String language , String input) {
        try {
            Path tempDir = Files.createTempDirectory("submission_");

            String filename = getFileName(language);
            Files.writeString(tempDir.resolve(filename) , code);

            Files.writeString(tempDir.resolve("input.txt" ) ,input);

            String imageName = getImageName(language);

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "docker" , "run",
                    "--rm",
                    "--memory" , "256m",
                    "--cpus","0.5",
                    "--network","none",
                    "-v",tempDir.toAbsolutePath() + ":/code",
                    "-i",
                    imageName
            );

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            if(!input.isEmpty()){
                process.getOutputStream().write(input.getBytes());
                process.getOutputStream().close();
            }

            boolean finished = process.waitFor(5 , TimeUnit.SECONDS);

            if(!finished){
                process.destroyForcibly();
                return "TLE";
            }

            String output = new String(process.getInputStream().readAllBytes());

            deleteDirectory(tempDir);

            return output.trim();
        } catch (Exception e) {
              return "ERROR : " + e.getMessage();
        }
    }
    private String getFileName(String language) {
        return switch (language) {
            case "PYTHON" -> "solution.py";
            case "JAVA" -> "solution.java";
            case "CPP" -> "solution.cpp";
            default -> throw new RuntimeException("Unsupported language");
        };
    }

    private String getImageName(String language) {
        return switch (language) {
            case "PYTHON" -> "judge-python";
            case "JAVA" -> "judge-java";
            case "CPP" -> "judge-cpp";
            default -> throw new RuntimeException("Unsupported language");
        };
    }
    private void deleteDirectory(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}