// TeamController.java
package com.wavemaker.portal.controller;

import com.wavemaker.portal.model.dto.TeamDTO;
import com.wavemaker.portal.model.dto.TeamMemberDTO;
import com.wavemaker.portal.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import com.wavemaker.portal.service.TeamMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Operation(summary = "Get all teams")
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @Operation(summary = "Create a new team")
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @Operation(summary = "Add member to team")
    @PostMapping("/{teamId}/members")
    public ResponseEntity<TeamMemberDTO> addMemberToTeam(
            @PathVariable Long teamId,
            @RequestBody TeamMemberDTO memberDTO) {
        return ResponseEntity.ok(teamService.addMemberToTeam(teamId, memberDTO));
    }

    @Operation(summary = "Get team members")
    @GetMapping("/{teamId}/members")
    public ResponseEntity<List<TeamMemberDTO>> getMembersByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamMemberService.getMembersByTeam(teamId));
    }

    @Operation(summary = "Update member role")
    @PutMapping("/members/{memberId}/admin")
    public ResponseEntity<TeamMemberDTO> updateMemberRole(
            @PathVariable Long memberId,
            @RequestParam Boolean isAdmin) {
        return ResponseEntity.ok(teamMemberService.updateMemberRole(memberId, isAdmin));
    }
}
