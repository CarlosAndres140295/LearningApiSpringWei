package com.andres.learnspringboot3wei.artifact.convert;

import com.andres.learnspringboot3wei.artifact.dto.ArtifactDTO;
import com.andres.learnspringboot3wei.artifact.model.Artifact;
import com.andres.learnspringboot3wei.wizard.convert.WizardToWizardDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Generate constructor inject WizardToWizardDTOConverter
public class ArtifactToArtifactDTOConverter implements Converter<Artifact, ArtifactDTO> {

    private final WizardToWizardDTOConverter wizardToArtifactDTOConverter;

    @Override
    public ArtifactDTO convert(Artifact source) {
        return new ArtifactDTO(source.getId(),
                                source.getName(),
                                source.getDescription(),
                                source.getImageUrl(),
                                source.getOwner() != null
                                        ? wizardToArtifactDTOConverter.convert(source.getOwner()): null);
    }
}
