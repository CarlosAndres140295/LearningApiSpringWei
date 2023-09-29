package com.andres.learnspringboot3wei.artifact.controller;

import com.andres.learnspringboot3wei.artifact.exception.ArtifactNotFoundException;
import com.andres.learnspringboot3wei.artifact.model.Artifact;
import com.andres.learnspringboot3wei.artifact.service.ArtifactService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ArtifactControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtifactService artifactService;

    List<Artifact> artifactList;

    @Value("${api.endpoint.base-url}") // Spring will go to application.yml to find the value and inject into this field.
    String baseUrl;

    @BeforeEach
    void setUp() {
        this.artifactList = new ArrayList<>();
        Artifact a =  new Artifact();

        a.setId("1");
        a.setName("test");
        a.setDescription("test");
        a.setImageUrl("test");
        this.artifactList.add(a);

        Artifact b =  new Artifact();
        b.setId("2");
        b.setName("test");
        b.setDescription("test");
        b.setImageUrl("test");
        this.artifactList.add(b);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindArtifactById() throws Exception {
        //Given
        given(this.artifactService.findById("1")).willReturn(this.artifactList.get(0));

        //When and then
        this.mockMvc.perform(get(this.baseUrl + "/artifacts/1").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.flag").value(Boolean.TRUE))
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.message").value("OK"))
                    .andExpect(jsonPath("$.data.id").value("1"))
                    .andExpect(jsonPath("$.data.name").value("test"));

    }


    @Test
    void testFindArtifactByIdNotFound() throws Exception {
        //Given
        given(this.artifactService.findById("3")).willThrow(new ArtifactNotFoundException("Resource not found"));

        //When and then
        this.mockMvc.perform(get(this.baseUrl + "/artifacts/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(Boolean.FALSE))
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("Resource not found"))
                .andExpect(jsonPath("$.data").isEmpty());

    }
}