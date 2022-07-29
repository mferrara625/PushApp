package com.mferrara.PushApp.repositories;

import com.mferrara.PushApp.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
