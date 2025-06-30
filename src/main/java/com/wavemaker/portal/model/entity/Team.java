package com.wavemaker.portal.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "prospect_id")
    private Prospect prospect;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
