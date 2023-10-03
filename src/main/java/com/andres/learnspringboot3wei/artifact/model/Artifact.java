package com.andres.learnspringboot3wei.artifact.model;


import com.andres.learnspringboot3wei.wizard.model.Wizard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artifacts")
public class Artifact implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;

    @ManyToOne
    private Wizard owner;
}
