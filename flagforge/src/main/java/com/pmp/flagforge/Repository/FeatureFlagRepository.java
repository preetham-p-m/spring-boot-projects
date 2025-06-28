package com.pmp.flagforge.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmp.flagforge.Model.FeatureFlag;

@Repository
public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, UUID> {

    boolean existsByFlagKey(String flagKey);

    Optional<FeatureFlag> findByFlagKey(String flagKey);
}
