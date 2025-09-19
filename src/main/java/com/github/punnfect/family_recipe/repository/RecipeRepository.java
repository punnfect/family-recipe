package com.github.punnfect.family_recipe.repository;

import com.github.punnfect.family_recipe.entities.Family;
import com.github.punnfect.family_recipe.entities.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @EntityGraph(attributePaths = {"ingredients", "instructions", "family"})
    List<Recipe> findByFamily(Family family);

    @EntityGraph(attributePaths = {"ingredients", "instructions", "family"})
    List<Recipe> findByNameContainingIgnoreCase(String name);

    @Override
    @EntityGraph(attributePaths = {"ingredients", "instructions", "family"})
    List<Recipe> findAll();
}