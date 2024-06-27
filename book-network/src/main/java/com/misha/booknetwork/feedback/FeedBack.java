package com.misha.booknetwork.feedback;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FeedBack {
   @Id
   @GeneratedValue
    private Integer id;

    private Double note; // 1- 5 stars

    private String comment; // feed back about book or whatever

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Integer createdBy;
    @LastModifiedDate
    @Column(insertable = false)
    private Integer lastModifiedBy;

}
