package com.wavemaker.portal.repository;

import com.wavemaker.portal.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByProspectId(Long prospectId);
    List<Team> findByTeamNameContainingIgnoreCase(String teamName);
}