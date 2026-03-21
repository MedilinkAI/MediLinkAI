package com.mediLinkAI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mediLinkAI.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<Profile, Long> {

       Optional<Profile> findByUserId(Long userId);

       @Query("SELECT p FROM Profile p WHERE p.user.id = :userId AND " +
                     "p.dietaryPreference IS NOT NULL")
       Optional<Profile> findByUserIdWithDietaryPreferences(@Param("userId") Long userId);

       @Query(value = "SELECT * FROM profiles WHERE user_id = :userId AND " +
                     "dietary_preference::text LIKE CONCAT('%', :preference, '%')", nativeQuery = true)
       List<Profile> findByUserIdAndDietaryPreference(@Param("userId") Long userId,
                     @Param("preference") String preference);

       @Query("SELECT p FROM Profile p WHERE p.activityLevel = :activityLevel")
       List<Profile> findByActivityLevel(@Param("activityLevel") String activityLevel);
}
