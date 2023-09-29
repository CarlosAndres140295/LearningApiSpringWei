package com.andres.learnspringboot3wei.artifact.exception;

public class ArtifactNotFoundException extends RuntimeException {
    public ArtifactNotFoundException(String message) {
        super(message);
    }
}
