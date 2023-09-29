package com.andres.learnspringboot3wei.artifact.repository;

import com.andres.learnspringboot3wei.artifact.model.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactRespository extends JpaRepository<Artifact, String> {}
