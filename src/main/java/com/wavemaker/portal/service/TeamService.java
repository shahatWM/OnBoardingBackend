package com.wavemaker.portal.service;

import com.wavemaker.portal.model.dto.TeamDTO;
import com.wavemaker.portal.model.dto.TeamMemberDTO;
import com.wavemaker.portal.model.entity.Team;
import com.wavemaker.portal.model.entity.Prospect;
import com.wavemaker.portal.model.entity.TeamMember;
import com.wavemaker.portal.repository.TeamRepository;
import com.wavemaker.portal.repository.ProspectRepository;
import com.wavemaker.portal.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProspectRepository prospectRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Transactional(readOnly = true)
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team team = new Team();
        updateTeamFromDTO(team, teamDTO);

        // Convert String to Long before findById
        Long prospectId = Long.parseLong(teamDTO.getProspectId());
        Prospect prospect = prospectRepository.findById(prospectId)
                .orElseThrow(() -> new RuntimeException("Prospect not found"));


        team.setProspect(prospect);

        Team savedTeam = teamRepository.save(team);
        return convertToDTO(savedTeam);
    }

    @Transactional
    public TeamMemberDTO addMemberToTeam(String teamIdStr, TeamMemberDTO memberDTO) {
        Long teamId = Long.parseLong(teamIdStr);
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));



        TeamMember member = new TeamMember();
        member.setTeam(team);
        member.setEmail(memberDTO.getEmail());
        member.setIsAdmin(memberDTO.getIsAdmin());

        TeamMember savedMember = teamMemberRepository.save(member);
        return convertToMemberDTO(savedMember);
    }

    private TeamDTO convertToDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setProspectId(String.valueOf(team.getProspect().getId()));
        dto.setTeamName(team.getTeamName());
        dto.setStartDate(team.getStartDate());
        dto.setEndDate(team.getEndDate());
        if (team.getTeamMembers() != null) {
            dto.setTeamMembers(team.getTeamMembers().stream()
                    .map(this::convertToMemberDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private TeamMemberDTO convertToMemberDTO(TeamMember member) {
        TeamMemberDTO dto = new TeamMemberDTO();
        dto.setId(member.getId().toString());
        dto.setTeamId(member.getTeam().getId().toString());
        dto.setEmail(member.getEmail());
        dto.setIsAdmin(member.getIsAdmin());
        return dto;
    }

    private void updateTeamFromDTO(Team team, TeamDTO dto) {
        team.setTeamName(dto.getTeamName());
        team.setStartDate(dto.getStartDate());
        team.setEndDate(dto.getEndDate());
    }
}
