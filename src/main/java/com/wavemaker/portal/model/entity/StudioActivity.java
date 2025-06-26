package com.wavemaker.portal.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "StudioActivity")
public class StudioActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private TeamMember member;

    @Column(name = "projects_created")
    private Integer projectsCreated = 0;

    @Column(name = "studio_usage_percentage")
    private BigDecimal studioUsagePercentage = BigDecimal.ZERO;
}
