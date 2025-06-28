package com.pmp.flagforge.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmp.flagforge.Model.FeatureFlag;
import com.pmp.flagforge.Model.FlagDefinition;
import com.pmp.flagforge.Service.FeatureFlagService;

import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/feature-flags")
public class FeatureFlagController {
    private final FeatureFlagService featureFlagService;

    public FeatureFlagController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FeatureFlag> patchUpdate(@PathVariable UUID id,
            @RequestBody FlagDefinition flagDefinition) {
        var featureFlag = featureFlagService.patchUpdate(id, flagDefinition);
        return ResponseEntity.ok(featureFlag);

    }

}
