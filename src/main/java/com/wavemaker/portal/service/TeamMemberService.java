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
    public List<TeamMemberDTO> getMembersByTeam(String teamId) {
        Long parsedTeamId = Long.parseLong(teamId);
        return teamMemberRepository.findByTeamId(parsedTeamId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamMemberDTO updateMemberRole(String memberId, Boolean isAdmin) {
        Long parsedId = Long.parseLong(memberId);
        TeamMember member = teamMemberRepository.findById(parsedId)
                .orElseThrow(() -> new RuntimeException("Team member not found"));

        member.setIsAdmin(isAdmin);
        TeamMember updated = teamMemberRepository.save(member);
        return convertToDTO(updated);
    }

    private TeamMemberDTO convertToDTO(TeamMember member) {
        TeamMemberDTO dto = new TeamMemberDTO();
        dto.setId(String.valueOf(member.getId()));
        dto.setTeamId(String.valueOf(member.getTeam().getId()));
        dto.setEmail(member.getEmail());
        dto.setIsAdmin(member.getIsAdmin());
        return dto;
    }
}
