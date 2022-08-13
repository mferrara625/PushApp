package com.mferrara.PushApp.repositories;

import com.mferrara.PushApp.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

//    List<StrengthExercise> findAllByType(Boolean type);
    Exercise existsByName(String name);
}
