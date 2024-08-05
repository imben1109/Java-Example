package org.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Bi-directional OneToMany/ManyToOne Relationship
    // mappedBy means the owning side is other class
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentReport> reports;

    public void addReport(StudentReport report){
        if (this.reports == null) {
            this.reports = new ArrayList<>();
        }
        report.setStudent(this);
        this.reports.add(report);
    }
}
