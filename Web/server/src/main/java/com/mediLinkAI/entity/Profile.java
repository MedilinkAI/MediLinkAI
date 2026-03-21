package com.mediLinkAI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles", indexes = {
        @Index(name = "idx_profile_user_id", columnList = "user_id")
})
@EntityListeners(AuditingEntityListener.class)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private Integer age;

    private Double height;

    private Double weight;

    private Double targetWeight;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String dietaryPreference;

    private String healthGoal;

    private Integer dailyCalorieTarget;

    private String allergies;

    private String restrictions;

    private String activityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "measurement_unit")
    private MeasurementUnit measurementUnit;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum MeasurementUnit {
        METRIC, IMPERIAL
    }
}
