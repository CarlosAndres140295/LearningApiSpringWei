package com.andres.learnspringboot3wei.artifact.dto;

import com.andres.learnspringboot3wei.wizard.dto.WizardDTO;

public record ArtifactDTO(String id,
                          String name,
                          String description,
                          String imageUrl,
                          WizardDTO owner) {
}
