package com.wavemaker.portal.repository;

import com.wavemaker.portal.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByProspectId(Long prospectId);
    List<Activity> findByActivityType(Activity.ActivityType activityType);
    List<Activity> findByCreatedById(Long userId);
}