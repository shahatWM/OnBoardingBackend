package com.wavemaker.portal.controller;

import com.wavemaker.portal.model.dto.ProspectDTO;
import com.wavemaker.portal.service.ProspectService;
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
@RequestMapping("/api/prospects")
@Tag(name = "Prospect Management", description = "APIs for managing prospects")
public class ProspectController {
    @Autowired
    private ProspectService prospectService;

    @Operation(
        summary = "Get all prospects",
        description = "Retrieves a list of all prospects in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all prospects",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProspectDTO.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    @GetMapping
    public ResponseEntity<List<ProspectDTO>> getAllProspects() {
        return ResponseEntity.ok(prospectService.getAllProspects());
    }

    @Operation(
        summary = "Create a new prospect",
        description = "Creates a new prospect with the provided details"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the prospect",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProspectDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    @PostMapping
    public ResponseEntity<ProspectDTO> createProspect(
            @Parameter(description = "Prospect details", required = true)
            @RequestBody ProspectDTO prospectDTO,
            
            @Parameter(description = "Email of the user creating the prospect", required = true)
            @RequestHeader("User-Email") String userEmail) {
        return ResponseEntity.ok(prospectService.createProspect(prospectDTO, userEmail));
    }
}