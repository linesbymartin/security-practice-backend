package com.linesbymartin.securitypractice.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant timestampCreate;

    @LastModifiedDate
    @Column()
    private Instant timestampUpdate;

    @PrePersist
    protected void onCreate() {
        setTimestampCreate(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        setTimestampUpdate(Instant.now());
    }
}
