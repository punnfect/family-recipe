package com.github.punnfect.family_recipe.controllers;

import com.github.punnfect.family_recipe.dto.RecipeDto;
import com.github.punnfect.family_recipe.dto.RecipeSummaryDto;
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

    @GetMapping("/summaries")
    public List<RecipeSummaryDto> getAllRecipeSummaries() {
        return recipeService.getAllRecipeSummaries();
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

    @GetMapping("/search")
    public List<RecipeSummaryDto> searchRecipes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String family) {

        if (name != null) {
            return recipeService.getRecipesByRecipeName(name);
        }

        if (family != null) {
            return recipeService.getRecipesByFamilyName(family);
        }

        return List.of();
    }

}