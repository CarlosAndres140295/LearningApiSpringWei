package com.andres.learnspringboot3wei.artifact.service;

import com.andres.learnspringboot3wei.artifact.exception.ArtifactNotFoundException;
import com.andres.learnspringboot3wei.artifact.model.Artifact;
import com.andres.learnspringboot3wei.artifact.repository.ArtifactRespository;
import com.andres.learnspringboot3wei.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock
    ArtifactRespository repository;

    @InjectMocks
    ArtifactService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        //Given. Arrange inputs and targets.
        Artifact a = new Artifact();
        a.setId("1");
        a.setName("test");
        a.setDescription("test");
        a.setImageUrl("imageUrl");

        Wizard w = new Wizard();
        w.setId(1);
        w.setName("Test");
        a.setOwner(w);

        given(repository.findById("1")).willReturn(Optional.of(a));

        //When. Act on the target behavior.
        Artifact returned = service.findById("1");

        //Then. Assert expected outcomes.
        assertThat(returned.getId()).isEqualTo(a.getId());
        assertThat(returned.getName()).isEqualTo(a.getName());
        assertThat(returned.getDescription()).isEqualTo(a.getDescription());
    }

    @Test
    void testFindByIdNotFound(){
        //Given. Arrange inputs and targets.
        given(repository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        //When. Act on the target behavior.
        Throwable thrown = catchThrowable(()->{
            Artifact returned = service.findById("2");
        });

        //Then. Assert expected outcomes.
        assertThat(thrown)
                .isInstanceOf(ArtifactNotFoundException.class)
                .hasMessage("Resource not found");
    }
}