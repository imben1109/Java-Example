package org.example.demo;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.model.ExerciseBook;
import org.example.demo.model.Student;
import org.example.demo.model.StudentReport;
import org.example.demo.reposity.ExcuseBookRepo;
import org.example.demo.reposity.StudentRepo;
import org.example.demo.reposity.StudentReportRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.support.TransactionTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Slf4j
public class StudentRepoTest {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentReportRepo studentReportRepo;

    @Autowired
    private ExcuseBookRepo excuseBookRepo;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("can save")
    public void testSave(){
        studentRepo.save(new Student());
        var count = studentRepo.count();
        assertEquals(1, count);
    }

    @Test
    @DisplayName("can save with reports")
    public void testSaveWithReports(){
        var studentId = transactionTemplate.execute(status -> {
            var entity = new Student();
            entity.addReport(new StudentReport());
            entity.addReport(new StudentReport());
            studentRepo.saveAndFlush(entity);
            return entity.getId();
        });

        transactionTemplate.execute(status -> {
            var studentCount = studentRepo.count();
            assertEquals(1, studentCount);

            var reportCount = studentReportRepo.count();
            assertEquals(2, reportCount);

            var student = studentRepo.findById(studentId).get();
            assertEquals(2, student.getReports().size());

            var students = studentRepo.findAll();
            assertEquals(2, students.get(0).getReports().size());

            return  null;
        });

    }

    @Test
    @DisplayName("can find student with excuse books")
    public void testFindStudentWithExcuseBooks(){
        var studentName = "My Name";

        var studentId = transactionTemplate.execute(status -> {
            var student = studentRepo.saveAndFlush(Student.builder().name(studentName).build());
            var book = excuseBookRepo.saveAndFlush(ExerciseBook.builder().studentName(studentName).build());
            log.info("student: {}", student);
            log.info("book: {}", book);
            return student.getId();
        });

        entityManager.clear(); // as it is unidirectional, the entity would be refreshed until persistent context is updated

        var result = transactionTemplate.execute(status -> studentRepo.findById(studentId).get());

        assertEquals(1, result.getExerciseBook().size());
    }
}
