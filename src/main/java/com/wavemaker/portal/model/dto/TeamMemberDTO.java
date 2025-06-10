package com.wavemaker.portal.model.dto;

import lombok.Data;

@Data
public class TeamMemberDTO {
    private Long id;
    private Long teamId;
    private String email;
    private Boolean isAdmin;
}