package com.andres.learnspringboot3wei.wizard.model;

import com.andres.learnspringboot3wei.artifact.model.Artifact;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wizards")
public class Wizard implements Serializable {

    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "owner",
                orphanRemoval = true,
                fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<Artifact> artifacts;

    public Integer getNumberOfArtifacts() {
        return this.artifacts.size();
    }
}
