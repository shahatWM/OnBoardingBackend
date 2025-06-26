package com.wavemaker.portal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "AcademyCourses")
public class AcademyCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String duration;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    public enum Difficulty {
        Beginner, Intermediate, Advanced
    }
}
