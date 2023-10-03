package com.andres.learnspringboot3wei.wizard.convert;

import com.andres.learnspringboot3wei.wizard.dto.WizardDTO;
import com.andres.learnspringboot3wei.wizard.model.Wizard;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WizardToWizardDTOConverter implements Converter<Wizard, WizardDTO> {

    @Override
    public WizardDTO convert(Wizard source) {
      return new WizardDTO(source.getId(),
                            source.getName(),
                            source.getNumberOfArtifacts());

    }
}
