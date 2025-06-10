package com.wavemaker.portal.repository;

import com.wavemaker.portal.model.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByMemberId(Long memberId);
    List<Enrollment> findByBundleId(Long bundleId);
    boolean existsByMemberIdAndBundleId(Long memberId, Long bundleId);
}