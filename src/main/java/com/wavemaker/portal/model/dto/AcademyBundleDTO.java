package com.wavemaker.portal.model.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AcademyBundleDTO {
    private Long id;
    private String bundleKey;
    private String name;
    private LocalDateTime createdAt;
    private List<AcademyCourseDTO> courses;
}