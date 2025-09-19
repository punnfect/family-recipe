package com.github.punnfect.family_recipe.mapper;

import com.github.punnfect.family_recipe.dto.*;
import com.github.punnfect.family_recipe.entities.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    public RecipeDto toDto(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeDto dto = new RecipeDto();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setPrepTimeMinutes(recipe.getPrepTimeMinutes());
        dto.setCookTimeMinutes(recipe.getCookTimeMinutes());
        dto.setServings(recipe.getServings());
        dto.setCreatedAt(recipe.getCreatedAt());

        if (recipe.getFamily() != null) {
            FamilyDto familyDto = new FamilyDto();
            familyDto.setName(recipe.getFamily().getName());
            dto.setFamily(familyDto);
        }

        dto.setInstructions(recipe.getInstructions().stream().map(instruction -> {
            InstructionDto instructionDto = new InstructionDto();
            instructionDto.setStepNumber(instruction.getStepNumber());
            instructionDto.setDescription(instruction.getDescription());
            return instructionDto;
        }).collect(Collectors.toList()));

        dto.setIngredients(recipe.getIngredients().stream().map(ingredient -> {
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setName(ingredient.getName());
            return ingredientDto;
        }).collect(Collectors.toSet()));

        return dto;
    }

    public RecipeSummaryDto toSummaryDto(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        RecipeSummaryDto summaryDto = new RecipeSummaryDto();
        summaryDto.setId(recipe.getId());
        summaryDto.setName(recipe.getName());
        return summaryDto;
    }
}