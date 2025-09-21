package com.github.punnfect.family_recipe.controllers;

import com.github.punnfect.family_recipe.dto.RecipeDto;
import com.github.punnfect.family_recipe.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecipeService recipeService;

    // Checks if a getById returns the correct info when an id exists
    @Test
    public void getRecipeById_whenRecipeExists_shouldReturnRecipe() throws Exception {

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(1);
        recipeDto.setName("Test Recipe");
        recipeDto.setDescription("A delicious test recipe.");

        when(recipeService.getRecipeById(1)).thenReturn(Optional.of(recipeDto));

        mockMvc.perform(get("/api/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Recipe"))
                .andExpect(jsonPath("$.description").value("A delicious test recipe."));
    }

    // Checks a getById for an id that does not exist, should return 404 to pass
    @Test
    public void getRecipeById_whenRecipeDoesNotExist_shouldReturnNotFound() throws Exception {

        when(recipeService.getRecipeById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/recipes/99"))
                .andExpect(status().isNotFound());
    }
}