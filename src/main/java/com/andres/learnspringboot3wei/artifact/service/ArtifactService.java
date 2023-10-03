package com.andres.learnspringboot3wei.artifact.service;

import com.andres.learnspringboot3wei.artifact.exception.ArtifactNotFoundException;
import com.andres.learnspringboot3wei.artifact.model.Artifact;
import com.andres.learnspringboot3wei.artifact.repository.ArtifactRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ArtifactService {

    private final ArtifactRespository repository;

    public Artifact findById(String artifactId) throws Exception{
         return this.repository
                 .findById(artifactId)
                 .orElseThrow(() -> new ArtifactNotFoundException("Resource not found"));

    }

    public List<Artifact> findAll() {
        return this.repository
                .findAll();
    }
}
