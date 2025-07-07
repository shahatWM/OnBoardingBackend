package com.wavemaker.portal.service;

import com.wavemaker.portal.model.dto.TeamMemberDTO;
import com.wavemaker.portal.model.entity.TeamMember;
import com.wavemaker.portal.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamMemberService {
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Transactional(readOnly = true)
    public List<TeamMemberDTO> getMembersByTeam(String teamIdStr) {
        Long teamId = Long.parseLong(teamIdStr);
        return teamMemberRepository.findByTeamId(teamId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamMemberDTO updateMemberRole(String memberIdStr, Boolean isAdmin) {
        Long parsedId = Long.parseLong(memberId);
        TeamMember member = teamMemberRepository.findById(parsedId)
                .orElseThrow(() -> new RuntimeException("Team member not found"));



        member.setIsAdmin(isAdmin);
        TeamMember updatedMember = teamMemberRepository.save(member);
        return convertToDTO(teamMemberRepository.findById(parsedId)
        .orElseThrow(() -> new RuntimeException("Team member not found")));

    }

    private TeamMemberDTO convertToDTO(TeamMember member) {
        TeamMemberDTO dto = new TeamMemberDTO();
        dto.setId(member.getId().toString());
        dto.setTeamId(member.getTeam().getId().toString());
        dto.setEmail(member.getEmail());
        dto.setIsAdmin(member.getIsAdmin());
        return dto;
    }
}
