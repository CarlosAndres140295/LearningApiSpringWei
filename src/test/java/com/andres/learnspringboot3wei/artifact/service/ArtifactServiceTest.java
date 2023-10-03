package com.andres.learnspringboot3wei.artifact.service;

import com.andres.learnspringboot3wei.artifact.exception.ArtifactNotFoundException;
import com.andres.learnspringboot3wei.artifact.model.Artifact;
import com.andres.learnspringboot3wei.artifact.repository.ArtifactRespository;
import com.andres.learnspringboot3wei.wizard.model.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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

    List<Artifact> artifactList;

    @BeforeEach
    void setUp() {
        this.artifactList = new ArrayList<>();
        Artifact artifact = new Artifact();
        artifact.setId("1");
        artifact.setName("test");
        artifact.setDescription("test");
        artifact.setImageUrl("imageUrl");

        Wizard w = new Wizard();
        w.setId(1);
        w.setName("Test");
        artifact.setOwner(w);

        artifactList.add(artifact);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        //Given. Arrange inputs and targets.
        given(repository.findById("1")).willReturn(Optional.of(artifactList.get(0)));

        //When. Act on the target behavior.
        Artifact returned = service.findById("1");

        //Then. Assert expected outcomes.
        assertThat(returned.getId()).isEqualTo(artifactList.get(0).getId());
        assertThat(returned.getName()).isEqualTo(artifactList.get(0).getName());
        assertThat(returned.getDescription()).isEqualTo(artifactList.get(0).getDescription());
    }

    @Test
    void testFindByIdNotFound() throws Exception{
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

    @Test
    void testFindAllArtifactsSuccess() throws Exception{
        //Given
        given(repository.findAll()).willReturn(this.artifactList);

        //When
        List<Artifact> returned = repository.findAll();

        //Then
        assertThat(returned.get(0).getName().equals(artifactList.get(0).getName()));
    }
}