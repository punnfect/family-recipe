package com.github.punnfect.family_recipe.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class RecipeDto {
    private Integer id;
    private String name;
    private String description;
    private Integer prepTimeMinutes;
    private Integer cookTimeMinutes;
    private Integer servings;
    private LocalDateTime createdAt;
    private FamilyDto family;
    private List<InstructionDto> instructions;
    private Set<IngredientDto> ingredients;
}