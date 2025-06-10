package com.wavemaker.portal.service;

import com.wavemaker.portal.model.dto.ProspectDTO;
import com.wavemaker.portal.model.entity.Prospect;
import com.wavemaker.portal.repository.ProspectRepository;
import com.wavemaker.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProspectService {
    @Autowired
    private ProspectRepository prospectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ProspectDTO> getAllProspects() {
        return prospectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProspectDTO createProspect(ProspectDTO prospectDTO, String userEmail) {
        Prospect prospect = new Prospect();
        updateProspectFromDTO(prospect, prospectDTO);
        
        userRepository.findByEmail(userEmail)
                .ifPresent(prospect::setCreatedBy);
        
        Prospect savedProspect = prospectRepository.save(prospect);
        return convertToDTO(savedProspect);
    }

    private ProspectDTO convertToDTO(Prospect prospect) {
        ProspectDTO dto = new ProspectDTO();
        dto.setId(prospect.getId());
        dto.setCompanyName(prospect.getCompanyName());
        dto.setCategory(prospect.getCategory());
        dto.setIndustry(prospect.getIndustry());
        dto.setUrl(prospect.getUrl());
        dto.setAddress(prospect.getAddress());
        dto.setHealth(prospect.getHealth());
        dto.setPocName(prospect.getPocName());
        dto.setPocPhone(prospect.getPocPhone());
        dto.setPocEmail(prospect.getPocEmail());
        if (prospect.getCreatedBy() != null) {
            dto.setCreatedByEmail(prospect.getCreatedBy().getEmail());
        }
        return dto;
    }

    private void updateProspectFromDTO(Prospect prospect, ProspectDTO dto) {
        prospect.setCompanyName(dto.getCompanyName());
        prospect.setCategory(dto.getCategory());
        prospect.setIndustry(dto.getIndustry());
        prospect.setUrl(dto.getUrl());
        prospect.setAddress(dto.getAddress());
        prospect.setHealth(dto.getHealth());
        prospect.setPocName(dto.getPocName());
        prospect.setPocPhone(dto.getPocPhone());
        prospect.setPocEmail(dto.getPocEmail());
    }
}