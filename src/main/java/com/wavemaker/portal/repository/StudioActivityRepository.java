package com.wavemaker.portal.repository;

import com.wavemaker.portal.model.entity.StudioActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudioActivityRepository extends JpaRepository<StudioActivity, Long> {
    Optional<StudioActivity> findByMemberId(Long memberId);
}