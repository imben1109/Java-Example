package org.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Bi-directional OneToMany/ManyToOne Relationship
    // mappedBy means the owning side is other class
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentReport> reports;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_name", referencedColumnName = "name")
    private List<ExerciseBook> exerciseBook;

    public void addReport(StudentReport report){
        if (this.reports == null) {
            this.reports = new ArrayList<>();
        }
        report.setStudent(this);
        this.reports.add(report);
    }
}
