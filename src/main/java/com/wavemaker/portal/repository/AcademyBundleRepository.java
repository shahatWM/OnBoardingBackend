package com.wavemaker.portal.repository;

import com.wavemaker.portal.model.entity.AcademyBundle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AcademyBundleRepository extends JpaRepository<AcademyBundle, Long> {
    Optional<AcademyBundle> findByBundleKey(String bundleKey);
    Optional<AcademyBundle> findByName(String name);
}