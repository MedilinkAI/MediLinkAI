package com.mediLinkAI.services.Diagnostic;

import com.mediLinkAI.dto.DiagnosticProfileDTO;
import com.mediLinkAI.entity.Diagnostic.DiagnosticProfile;
import com.mediLinkAI.entity.Master.Location;
import com.mediLinkAI.entity.User.User;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Diagnostic.DiagnosticProfileRepository;
import com.mediLinkAI.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service(value = "diagnosticProfileService")
@Transactional
public class DiagnosticProfileServiceImpl implements DiagnosticProfileService {

    @Autowired
    private DiagnosticProfileRepository diagnosticProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DiagnosticProfileDTO createProfile(DiagnosticProfileDTO dto) throws MediLinkAI {
        if (diagnosticProfileRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new MediLinkAI("PROFILE_ALREADY_EXISTS");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new MediLinkAI("USER_NOT_FOUND"));

        DiagnosticProfile profile = new DiagnosticProfile();
        profile.setUser(user);
        profile.setCenterName(dto.getCenterName());
        profile.setCenterImageUrl(dto.getCenterImageUrl());
        profile.setContactNumber(dto.getContactNumber());
        profile.setAccreditation(dto.getAccreditation());

        if (dto.getLocationId() != null) {
            Location loc = new Location();
            loc.setId(dto.getLocationId());
            profile.setLocation(loc);
        }

        profile = diagnosticProfileRepository.save(profile);
        return mapToDTO(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public DiagnosticProfileDTO getProfileByUserId(UUID userId) throws MediLinkAI {
        DiagnosticProfile profile = diagnosticProfileRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));
        return mapToDTO(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public DiagnosticProfileDTO getProfileById(UUID id) throws MediLinkAI {
        DiagnosticProfile profile = diagnosticProfileRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));
        return mapToDTO(profile);
    }

    @Override
    public DiagnosticProfileDTO updateProfile(UUID userId, DiagnosticProfileDTO dto) throws MediLinkAI {
        DiagnosticProfile profile = diagnosticProfileRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));

        // Use explicit update query to bypass bad SQL grammar during Hibernate auto-flush
        String centerNameToUpdate = dto.getCenterName() != null ? dto.getCenterName() : profile.getCenterName();
        String imageUrlToUpdate = dto.getCenterImageUrl() != null ? dto.getCenterImageUrl() : profile.getCenterImageUrl();
        String contactNumToUpdate = dto.getContactNumber() != null ? dto.getContactNumber() : profile.getContactNumber();
        String accrToUpdate = dto.getAccreditation() != null ? dto.getAccreditation() : profile.getAccreditation();
        UUID locToUpdate = dto.getLocationId() != null ? dto.getLocationId() : (profile.getLocation() != null ? profile.getLocation().getId() : null);

        diagnosticProfileRepository.updateProfileExplicitly(
                userId,
                centerNameToUpdate,
                imageUrlToUpdate,
                locToUpdate,
                contactNumToUpdate,
                accrToUpdate
        );

        // Fetch the perfectly updated profile from DB to return
        profile = diagnosticProfileRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));

        return mapToDTO(profile);
    }

    @Override
    public void deleteProfile(UUID userId) throws MediLinkAI {
        DiagnosticProfile profile = diagnosticProfileRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));
        profile.setIsDeleted(true);
        diagnosticProfileRepository.save(profile);
    }

    private DiagnosticProfileDTO mapToDTO(DiagnosticProfile profile) {
        DiagnosticProfileDTO dto = new DiagnosticProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser() != null ? profile.getUser().getId() : null);
        dto.setCenterName(profile.getCenterName());
        dto.setCenterImageUrl(profile.getCenterImageUrl());
        dto.setLocationId(profile.getLocation() != null ? profile.getLocation().getId() : null);
        dto.setContactNumber(profile.getContactNumber());
        dto.setAccreditation(profile.getAccreditation());
        dto.setCreatedAt(profile.getCreatedAt());
        dto.setUpdatedAt(profile.getUpdatedAt());
        dto.setIsDeleted(profile.getIsDeleted());
        return dto;
    }
}
