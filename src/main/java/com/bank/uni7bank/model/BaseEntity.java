package com.bank.uni7bank.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date criadoEm;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificadoEm;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getModifiedAt() {
        return ZonedDateTime.ofInstant(modificadoEm.toInstant(), ZoneId.systemDefault());
    }
}