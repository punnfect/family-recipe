package com.github.punnfect.family_recipe.services;


import com.github.punnfect.family_recipe.dto.RecipeDto;
import com.github.punnfect.family_recipe.dto.RecipeSummaryDto;
import com.github.punnfect.family_recipe.entities.Family;
import com.github.punnfect.family_recipe.mapper.RecipeMapper;
import com.github.punnfect.family_recipe.repository.FamilyRepository;
import com.github.punnfect.family_recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private RecipeMapper recipeMapper;

    @Transactional(readOnly = true)
    public List<RecipeDto> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecipeSummaryDto> getAllRecipeSummaries() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<RecipeDto> getRecipeById(Integer id) {
        return recipeRepository.findById(id).map(recipeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<RecipeDto> getRecipesByFamilyId(Integer familyId) {
        Optional<Family> familyOptional = familyRepository.findById(familyId);
        if (familyOptional.isPresent()) {
            return recipeRepository.findByFamily(familyOptional.get()).stream()
                    .map(recipeMapper::toDto)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Transactional(readOnly = true)
    public List<RecipeSummaryDto> getRecipesByRecipeName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name).stream()
                .map(recipeMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecipeSummaryDto> getRecipesByFamilyName(String familyName) {
        Optional<Family> familyOptional = familyRepository.findByNameIgnoreCase(familyName);
        if (familyOptional.isPresent()) {
            return recipeRepository.findByFamily(familyOptional.get()).stream()
                    .map(recipeMapper::toSummaryDto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}