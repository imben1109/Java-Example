package org.example.demo.reposity;

import org.example.demo.model.ExerciseBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcuseBookRepo extends JpaRepository<ExerciseBook, Long> {
}
