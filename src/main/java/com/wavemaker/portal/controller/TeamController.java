package com.wavemaker.portal.controller;

import com.wavemaker.portal.model.dto.TeamDTO;
import com.wavemaker.portal.model.dto.TeamMemberDTO;
import com.wavemaker.portal.service.TeamMemberService;
import com.wavemaker.portal.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Team APIs", description = "APIs related to team and members")
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping
    @Operation(summary = "Get all teams")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @PostMapping
    @Operation(summary = "Create a new team")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @PostMapping("/{teamId}/members")
    @Operation(summary = "Add a member to a team")
    public ResponseEntity<TeamMemberDTO> addMemberToTeam(
            @PathVariable Long teamId,
            @RequestBody TeamMemberDTO memberDTO) {
        return ResponseEntity.ok(teamService.addMemberToTeam(teamId, memberDTO));
    }

    @GetMapping("/{teamId}/members")
    @Operation(summary = "Get all members of a team")
    public ResponseEntity<List<TeamMemberDTO>> getTeamMembers(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamMemberService.getMembersByTeam(teamId));
    }

    @PutMapping("/members/{memberId}/role")
    @Operation(summary = "Update member role (admin or not)")
    public ResponseEntity<TeamMemberDTO> updateMemberRole(
            @PathVariable String memberId,
            @RequestParam Boolean isAdmin) {
        return ResponseEntity.ok(teamMemberService.updateMemberRole(memberId, isAdmin));
    }
}
