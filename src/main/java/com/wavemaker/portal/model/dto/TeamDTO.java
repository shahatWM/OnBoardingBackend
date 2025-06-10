package com.wavemaker.portal.model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TeamDTO {
    private Long id;
    private Long prospectId;
    private String teamName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TeamMemberDTO> teamMembers;
}