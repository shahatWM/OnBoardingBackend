package com.wavemaker.portal.service;

import com.wavemaker.portal.model.dto.AcademyBundleDTO;
import com.wavemaker.portal.model.dto.AcademyCourseDTO;
import com.wavemaker.portal.model.entity.AcademyBundle;
import com.wavemaker.portal.model.entity.AcademyCourse;
import com.wavemaker.portal.model.entity.Enrollment;
import com.wavemaker.portal.model.entity.TeamMember;
import com.wavemaker.portal.repository.AcademyBundleRepository;
import com.wavemaker.portal.repository.AcademyCourseRepository;
import com.wavemaker.portal.repository.EnrollmentRepository;
import com.wavemaker.portal.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademyService {
    @Autowired
    private AcademyBundleRepository bundleRepository;

    @Autowired
    private AcademyCourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Transactional(readOnly = true)
    public List<AcademyBundleDTO> getAllBundles() {
        return bundleRepository.findAll().stream()
                .map(this::convertToBundleDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AcademyCourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToCourseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AcademyBundleDTO createBundle(AcademyBundleDTO bundleDTO) {
        AcademyBundle bundle = new AcademyBundle();
        updateBundleFromDTO(bundle, bundleDTO);
        AcademyBundle savedBundle = bundleRepository.save(bundle);
        return convertToBundleDTO(savedBundle);
    }

    @Transactional
    public void enrollMember(Long memberId, Long bundleId) {
        if (enrollmentRepository.existsByMemberIdAndBundleId(memberId, bundleId)) {
            throw new RuntimeException("Member is already enrolled in this bundle");
        }

        TeamMember member = teamMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Team member not found"));
        
        AcademyBundle bundle = bundleRepository.findById(bundleId)
                .orElseThrow(() -> new RuntimeException("Bundle not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setMember(member);
        enrollment.setBundle(bundle);
        enrollmentRepository.save(enrollment);
    }

    private AcademyBundleDTO convertToBundleDTO(AcademyBundle bundle) {
        AcademyBundleDTO dto = new AcademyBundleDTO();
        dto.setId(bundle.getId());
        dto.setBundleKey(bundle.getBundleKey());
        dto.setName(bundle.getName());
        dto.setCreatedAt(bundle.getCreatedAt());
        return dto;
    }

    private AcademyCourseDTO convertToCourseDTO(AcademyCourse course) {
        AcademyCourseDTO dto = new AcademyCourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDuration(course.getDuration());
        dto.setDifficulty(course.getDifficulty());
        return dto;
    }

    private void updateBundleFromDTO(AcademyBundle bundle, AcademyBundleDTO dto) {
        bundle.setBundleKey(dto.getBundleKey());
        bundle.setName(dto.getName());
    }
}