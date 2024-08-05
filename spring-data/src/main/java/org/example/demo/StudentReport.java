package org.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    @ManyToOne
    private Student student;

}
