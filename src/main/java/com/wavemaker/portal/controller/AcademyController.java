package com.wavemaker.portal.controller;

import com.wavemaker.portal.model.dto.AcademyBundleDTO;
import com.wavemaker.portal.model.dto.AcademyCourseDTO;
import com.wavemaker.portal.service.AcademyService;
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
@RequestMapping("/api/academy")
@Tag(name = "Academy Management", description = "APIs for managing academy bundles, courses, and enrollments")
public class AcademyController {
    @Autowired
    private AcademyService academyService;

    @Operation(
        summary = "Get all academy bundles",
        description = "Retrieves a list of all available academy bundles"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all bundles",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AcademyBundleDTO.class))
        )
    })
    @GetMapping("/bundles")
    public ResponseEntity<List<AcademyBundleDTO>> getAllBundles() {
        return ResponseEntity.ok(academyService.getAllBundles());
    }

    @Operation(
        summary = "Get all academy courses",
        description = "Retrieves a list of all available academy courses"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all courses",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AcademyCourseDTO.class))
        )
    })
    @GetMapping("/courses")
    public ResponseEntity<List<AcademyCourseDTO>> getAllCourses() {
        return ResponseEntity.ok(academyService.getAllCourses());
    }

    @Operation(
        summary = "Create a new academy bundle",
        description = "Creates a new academy bundle with the provided details"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the bundle",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AcademyBundleDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data"
        )
    })
    @PostMapping("/bundles")
    public ResponseEntity<AcademyBundleDTO> createBundle(
            @Parameter(description = "Bundle details", required = true)
            @RequestBody AcademyBundleDTO bundleDTO) {
        return ResponseEntity.ok(academyService.createBundle(bundleDTO));
    }

    @Operation(
        summary = "Enroll a member in a bundle",
        description = "Enrolls a team member in an academy bundle"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully enrolled the member"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Member is already enrolled in this bundle"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Member or bundle not found"
        )
    })
    @PostMapping("/enroll/{memberId}/{bundleId}")
    public ResponseEntity<Void> enrollMember(
            @Parameter(description = "ID of the team member", required = true)
            @PathVariable Long memberId,
            
            @Parameter(description = "ID of the academy bundle", required = true)
            @PathVariable Long bundleId) {
        academyService.enrollMember(memberId, bundleId);
        return ResponseEntity.ok().build();
    }
}