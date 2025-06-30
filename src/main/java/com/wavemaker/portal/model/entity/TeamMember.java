package com.wavemaker.portal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TeamMembers")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String email;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private StudioActivity studioActivity;
}
