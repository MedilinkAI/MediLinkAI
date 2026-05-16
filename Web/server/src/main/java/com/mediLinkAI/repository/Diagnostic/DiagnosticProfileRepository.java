package com.mediLinkAI.repository.Diagnostic;

import com.mediLinkAI.entity.Diagnostic.DiagnosticProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiagnosticProfileRepository extends JpaRepository<DiagnosticProfile, UUID> {
    Optional<DiagnosticProfile> findByUserId(UUID userId);
    Optional<DiagnosticProfile> findByIdAndIsDeletedFalse(UUID id);
    Optional<DiagnosticProfile> findByUserIdAndIsDeletedFalse(UUID userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE DiagnosticProfile d SET d.centerName = :centerName, d.centerImageUrl = :url, " +
           "d.location.id = :locationId, d.contactNumber = :contactNumber, " +
           "d.accreditation = :accreditation WHERE d.user.id = :userId")
    int updateProfileExplicitly(@Param("userId") UUID userId,
                                @Param("centerName") String centerName,
                                @Param("url") String url,
                                @Param("locationId") UUID locationId,
                                @Param("contactNumber") String contactNumber,
                                @Param("accreditation") String accreditation);
}
