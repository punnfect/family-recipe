package com.github.punnfect.family_recipe.repository;

import com.github.punnfect.family_recipe.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {
}