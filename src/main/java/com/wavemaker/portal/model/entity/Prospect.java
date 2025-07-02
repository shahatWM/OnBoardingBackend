package com.wavemaker.portal.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Prospects")
public class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Industry industry;

    private String url;
    private String address;

    @Enumerated(EnumType.STRING)
    private Health health = Health.Active;

    @Column(name = "poc_name")
    private String pocName;

    @Column(name = "poc_phone")
    private String pocPhone;

    @Column(name = "poc_email")
    private String pocEmail;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime updated;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }

    public enum Category {
        SI, ISV, Enterprise
    }

    public enum Industry {
        Fintech, Telecomunication, Banking, Automotive, HealthCare, Manufacturing, SupplyChain
    }

    public enum Health {
        Active, Sleeping, Lost
    }
}
