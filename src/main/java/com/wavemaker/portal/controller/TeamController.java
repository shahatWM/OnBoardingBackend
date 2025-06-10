package com.wavemaker.portal.controller;

import com.wavemaker.portal.model.dto.TeamDTO;
import com.wavemaker.portal.model.dto.TeamMemberDTO;
import com.wavemaker.portal.service.TeamService;
import com.wavemaker.portal.service.TeamMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Team Management", description = "APIs for managing teams and team members")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Operation(
        summary = "Get all teams",
        description = "Retrieves a list of all teams in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all teams",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TeamDTO.class))
        )
    })
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @Operation(
        summary = "Create a new team",
        description = "Creates a new team with the provided details"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the team",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TeamDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data"
        )
    })
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(
            @Parameter(description = "Team details", required = true)
            @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @Operation(
        summary = "Add a member to a team",
        description = "Adds a new member to an existing team"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully added the member to the team",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TeamMemberDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Team not found"
        )
    })
    @PostMapping("/{teamId}/members")
    public ResponseEntity<TeamMemberDTO> addMemberToTeam(
            @Parameter(description = "ID of the team", required = true)
            @PathVariable Long teamId,
            
            @Parameter(description = "Member details", required = true)
            @RequestBody TeamMemberDTO memberDTO) {
        return ResponseEntity.ok(teamService.addMemberToTeam(teamId, memberDTO));
    }

    @Operation(
        summary = "Get team members",
        description = "Retrieves all members of a specific team"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved team members",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TeamMemberDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Team not found"
        )
    })
    @GetMapping("/{teamId}/members")
    public ResponseEntity<List<TeamMemberDTO>> getTeamMembers(
            @Parameter(description = "ID of the team", required = true)
            @PathVariable Long teamId) {
        return ResponseEntity.ok(teamMemberService.getMembersByTeam(teamId));
    }

    @Operation(
        summary = "Update member role",
        description = "Updates the admin status of a team member"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated member role",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TeamMemberDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Team member not found"
        )
    })
    @PutMapping("/members/{memberId}/role")
    public ResponseEntity<TeamMemberDTO> updateMemberRole(
            @Parameter(description = "ID of the team member", required = true)
            @PathVariable Long memberId,
            
            @Parameter(description = "Admin status to set", required = true)
            @RequestParam Boolean isAdmin) {
        return ResponseEntity.ok(teamMemberService.updateMemberRole(memberId, isAdmin));
    }
}