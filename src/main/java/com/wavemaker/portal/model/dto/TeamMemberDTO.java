package com.wavemaker.portal.model.dto;

import lombok.Data;

@Data
public class TeamMemberDTO {
    private String id;            // UUID or temporary string from frontend like "member-0"
    private Long teamId;          // Can be null for creation
    private String email;
    private String firstName;     // <-- add this
    private String lastName;      // <-- and this
    private Boolean isAdmin;
}
