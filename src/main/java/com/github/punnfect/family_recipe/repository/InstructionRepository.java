package com.github.punnfect.family_recipe.repository;

import com.github.punnfect.family_recipe.entities.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
}