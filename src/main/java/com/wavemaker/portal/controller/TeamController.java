package com.wavemaker.portal.controller;

import com.wavemaker.portal.model.dto.TeamDTO;
import com.wavemaker.portal.model.dto.TeamMemberDTO;
import com.wavemaker.portal.service.TeamMemberService;
import com.wavemaker.portal.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public TeamDTO createTeam(@RequestBody TeamDTO teamDTO) {
        return teamService.createTeam(teamDTO);
    }

    @PostMapping("/{teamId}/members")
    public TeamMemberDTO addMemberToTeam(@PathVariable String teamId, @RequestBody TeamMemberDTO memberDTO) {
        return teamService.addMemberToTeam(teamId, memberDTO);
    }

    @GetMapping("/{teamId}/members")
    public List<TeamMemberDTO> getMembersByTeam(@PathVariable String teamId) {
        return teamMemberService.getMembersByTeam(teamId);
    }

    @PutMapping("/members/{memberId}/role")
    @Operation(summary = "Update team member's role (admin or not)")
    public TeamMemberDTO updateMemberRole(@PathVariable String memberId, @RequestParam Boolean isAdmin) {
        return teamMemberService.updateMemberRole(memberId, isAdmin);
    }
}
