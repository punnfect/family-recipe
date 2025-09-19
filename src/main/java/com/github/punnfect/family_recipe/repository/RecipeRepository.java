package com.github.punnfect.family_recipe.repository;

import com.github.punnfect.family_recipe.entities.Family;
import com.github.punnfect.family_recipe.entities.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByFamily(Family family);

    @Override
    @EntityGraph(attributePaths = {"ingredients", "instructions", "family"})
    List<Recipe> findAll();
}