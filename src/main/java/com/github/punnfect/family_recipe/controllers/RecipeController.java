package com.github.punnfect.family_recipe.controllers;

import com.github.punnfect.family_recipe.dto.RecipeDto;
import com.github.punnfect.family_recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Integer id) {
        Optional<RecipeDto> recipe = recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/family/{familyId}")
    public List<RecipeDto> getRecipesByFamily(@PathVariable Integer familyId) {
        return recipeService.getRecipesByFamilyId(familyId);
    }
}