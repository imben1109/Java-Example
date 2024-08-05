package org.example.demo;

import org.example.demo.model.Student;
import org.example.demo.model.StudentReport;
import org.example.demo.reposity.StudentRepo;
import org.example.demo.reposity.StudentReportRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.support.TransactionTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest(showSql = true)
public class StudentRepoTest {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentReportRepo studentReportRepo;

    @Autowired
    private TransactionTemplate transactionTemplate;

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
        transactionTemplate.execute(status -> {
            var student = new Student();
            student.addReport(new StudentReport());
            student.addReport(new StudentReport());
            studentRepo.save(student);
            return null;
        });

        var studentCount = studentRepo.count();
        assertEquals(1, studentCount);

        var reportCount = studentReportRepo.count();
        assertEquals(2, reportCount);
    }
}
