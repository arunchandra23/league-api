package com.woxsen.leagueapi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woxsen.leagueapi.utils.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Branch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private Schools school;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean activeIndex;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "created_date")
    private Timestamp createdDate;
}
