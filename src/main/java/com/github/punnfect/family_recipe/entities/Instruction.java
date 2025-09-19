package com.github.punnfect.family_recipe.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "instructions")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "step_number")
    private int stepNumber;

    @Lob
    private String description;
}