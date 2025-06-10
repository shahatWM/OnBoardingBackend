package com.wavemaker.portal.service;

import com.wavemaker.portal.model.entity.Activity;
import com.wavemaker.portal.model.entity.Prospect;
import com.wavemaker.portal.model.entity.User;
import com.wavemaker.portal.repository.ActivityRepository;
import com.wavemaker.portal.repository.ProspectRepository;
import com.wavemaker.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ProspectRepository prospectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Activity createActivity(Long prospectId, Activity.ActivityType type, 
                                 String description, String userEmail) {
        Prospect prospect = prospectRepository.findById(prospectId)
                .orElseThrow(() -> new RuntimeException("Prospect not found"));
        
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Activity activity = new Activity();
        activity.setProspect(prospect);
        activity.setActivityType(type);
        activity.setDescription(description);
        activity.setActivityDate(LocalDateTime.now());
        activity.setCreatedBy(user);

        return activityRepository.save(activity);
    }

    @Transactional(readOnly = true)
    public List<Activity> getActivitiesByProspect(Long prospectId) {
        return activityRepository.findByProspectId(prospectId);
    }

    @Transactional(readOnly = true)
    public List<Activity> getActivitiesByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return activityRepository.findByCreatedById(user.getId());
    }
}