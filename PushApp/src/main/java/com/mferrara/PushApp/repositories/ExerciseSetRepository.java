package com.mferrara.PushApp.repositories;

import com.mferrara.PushApp.models.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
}
