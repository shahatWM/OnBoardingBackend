package com.wavemaker.portal.repository;

import com.wavemaker.portal.model.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProspectRepository extends JpaRepository<Prospect, Long> {
    List<Prospect> findByCompanyNameContainingIgnoreCase(String companyName);
    List<Prospect> findByCategory(Prospect.Category category);
    List<Prospect> findByIndustry(Prospect.Industry industry);
}