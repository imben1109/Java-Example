package org.example.demo.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.model.Student;
import org.example.demo.reposity.StudentRepo;
import org.example.demo.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    private final StudentRepo studentRepo;

    private final SomeService someService;

    @Autowired
    public StudentService(StudentRepo studentRepo, SomeService someService) {
        this.studentRepo = studentRepo;
        this.someService = someService;
    }
    
    @Transactional
    public Student updateStudentWithNestedException() {
        var student = studentRepo.findById(1L).orElseThrow();
        student.setName("Student Name");
        try {
            someService.throwException();
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return studentRepo.save(student);
    }


}
