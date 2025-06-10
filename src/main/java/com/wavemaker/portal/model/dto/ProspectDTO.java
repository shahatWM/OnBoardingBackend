package com.wavemaker.portal.model.dto;

import com.wavemaker.portal.model.entity.Prospect;
import lombok.Data;

@Data
public class ProspectDTO {
    private Long id;
    private String companyName;
    private Prospect.Category category;
    private Prospect.Industry industry;
    private String url;
    private String address;
    private Prospect.Health health;
    private String pocName;
    private String pocPhone;
    private String pocEmail;
    private String createdByEmail;
}