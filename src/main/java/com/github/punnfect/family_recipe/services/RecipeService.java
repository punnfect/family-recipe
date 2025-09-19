package com.github.punnfect.family_recipe.services;

import com.github.punnfect.family_recipe.entities.Family;
import com.github.punnfect.family_recipe.entities.Recipe;
import com.github.punnfect.family_recipe.repository.FamilyRepository;
import com.github.punnfect.family_recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private FamilyRepository familyRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Integer id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipesByFamilyId(Integer familyId) {
        Optional<Family> familyOptional = familyRepository.findById(familyId);
        if (familyOptional.isPresent()) {
            return recipeRepository.findByFamily(familyOptional.get());
        } else {
            return Collections.emptyList();
        }
    }
}