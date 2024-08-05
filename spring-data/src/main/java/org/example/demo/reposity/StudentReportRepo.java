package org.example.demo.reposity;

import org.example.demo.model.StudentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentReportRepo extends JpaRepository<StudentReport, Long> {
}
