package com.wavemaker.portal.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "prospect_id")
    private Prospect prospect;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;

    private String description;

    @Column(name = "activity_date")
    private LocalDateTime activityDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    public enum ActivityType {
        TeamCreated, Meeting, MOM, LicenseCreated, Other
    }
}
