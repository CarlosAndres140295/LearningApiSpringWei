package com.andres.learnspringboot3wei.artifact.controller;

import com.andres.learnspringboot3wei.artifact.exception.ArtifactNotFoundException;
import com.andres.learnspringboot3wei.artifact.model.Artifact;
import com.andres.learnspringboot3wei.artifact.service.ArtifactService;
import com.andres.learnspringboot3wei.system.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/artifacts")
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtifactService service;

    @GetMapping("/{artifactId}")
    public Result findArtifactById(@PathVariable(value = "artifactId") String artifactId) throws Exception {
        Artifact artifact = service.findById(artifactId);
        return new Result(true, 200, "OK", artifact);
    }
}
