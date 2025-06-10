package com.wavemaker.portal.repository;

import com.wavemaker.portal.model.entity.AcademyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AcademyCourseRepository extends JpaRepository<AcademyCourse, Long> {
    List<AcademyCourse> findByDifficulty(AcademyCourse.Difficulty difficulty);
    List<AcademyCourse> findByTitleContainingIgnoreCase(String title);
}