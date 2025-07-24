package com.wavemaker.portal.model.dto;

import com.wavemaker.portal.model.entity.Prospect;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProspectDTO {

    private Long id;

    // Basic Info
    private String companyName;
    private Prospect.Category category;
    private Prospect.Industry industry;
    private String url;
    private String address;
    private Prospect.Health health;

    // Point of Contact
    private String pocName;
    private String pocPhone;
    private String pocEmail;

    // Created by
    private String createdByEmail;

    // Team Info
    private String teamName;
    private LocalDate startDate;
    private LocalDate endDate;

    // Members
    private List<TeamMemberDTO> teamMembers;

    // Course Assignments
    // memberId -> list of course ids
    private Map<String, List<String>> memberCourses;
}
