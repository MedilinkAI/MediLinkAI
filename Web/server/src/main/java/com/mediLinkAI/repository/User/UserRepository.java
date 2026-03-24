package com.mediLinkAI.repository.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

import com.mediLinkAI.entity.User.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByPhoneHash(String phoneHash);

    boolean existsByPhoneHash(String phoneHash);


    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmailWithProfile(@Param("email") String email);
}
