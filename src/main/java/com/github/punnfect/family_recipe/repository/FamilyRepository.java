package com.github.punnfect.family_recipe.repository;

import com.github.punnfect.family_recipe.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

    Optional<Family> findByNameIgnoreCase(String name);

}