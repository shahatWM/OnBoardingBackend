package com.wavemaker.portal.controller;

import com.wavemaker.portal.model.entity.Activity;
import com.wavemaker.portal.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@Tag(name = "Activity Management", description = "APIs for managing prospect activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @Operation(
        summary = "Create a new activity",
        description = "Creates a new activity for a prospect"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the activity",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Activity.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Prospect or user not found"
        )
    })
    @PostMapping("/prospect/{prospectId}")
    public ResponseEntity<Activity> createActivity(
            @Parameter(description = "ID of the prospect", required = true)
            @PathVariable Long prospectId,
            
            @Parameter(description = "Type of activity", required = true)
            @RequestParam Activity.ActivityType type,
            
            @Parameter(description = "Activity description", required = true)
            @RequestParam String description,
            
            @Parameter(description = "Email of the user creating the activity", required = true)
            @RequestHeader("User-Email") String userEmail) {
        return ResponseEntity.ok(activityService.createActivity(prospectId, type, description, userEmail));
    }

    @Operation(
        summary = "Get activities by prospect",
        description = "Retrieves all activities for a specific prospect"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved activities",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Activity.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Prospect not found"
        )
    })
    @GetMapping("/prospect/{prospectId}")
    public ResponseEntity<List<Activity>> getActivitiesByProspect(
            @Parameter(description = "ID of the prospect", required = true)
            @PathVariable Long prospectId) {
        return ResponseEntity.ok(activityService.getActivitiesByProspect(prospectId));
    }

    @Operation(
        summary = "Get activities by user",
        description = "Retrieves all activities created by a specific user"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved activities",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Activity.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "User not found"
        )
    })
    @GetMapping("/user")
    public ResponseEntity<List<Activity>> getActivitiesByUser(
            @Parameter(description = "Email of the user", required = true)
            @RequestHeader("User-Email") String userEmail) {
        return ResponseEntity.ok(activityService.getActivitiesByUser(userEmail));
    }
}