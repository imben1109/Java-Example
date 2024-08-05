package org.example.demo;

import org.example.demo.model.Student;
import org.example.demo.reposity.StudentRepo;
import org.example.demo.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;


@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepo studentRepo;

    @Test
    @DisplayName("can throw UnexpectedRollbackException with nested class throwing RuntimeException")
    public void testUpdateStudentWithNestedException(){
        this.studentRepo.save(new Student());
        Assertions.assertThrows(UnexpectedRollbackException.class, () ->
                studentService.updateStudentWithNestedException()
        );
    }
}
