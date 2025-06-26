package com.wavemaker.portal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private TeamMember member;

    @ManyToOne
    @JoinColumn(name = "bundle_id")
    private AcademyBundle bundle;
}
