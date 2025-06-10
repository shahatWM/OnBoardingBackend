package com.wavemaker.portal.model.dto;

import com.wavemaker.portal.model.entity.AcademyCourse;
import lombok.Data;

@Data
public class AcademyCourseDTO {
    private Long id;
    private String title;
    private String duration;
    private AcademyCourse.Difficulty difficulty;
}