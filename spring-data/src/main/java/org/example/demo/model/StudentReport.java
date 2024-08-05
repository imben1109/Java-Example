package org.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class StudentReport {

    @Id
    @GeneratedValue
    private Long id;

    // Bi-directional OneToMany/ManyToOne Relationship
    // JoinColumn means the owning side is this class
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
