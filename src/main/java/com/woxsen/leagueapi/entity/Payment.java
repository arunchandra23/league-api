package com.woxsen.leagueapi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID id;

    private String amount;

    private String txnid;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "payment")
    private Bookings bookings;

    @Column(name = "mode")
    private String mode;
    @Column(name = "error")
    private String error;
    @Column(name = "error_message")
    private String error_message;
    @Column(name = "unmappedstatus")
    private String unmappedstatus;
    private String key;
    private String productinfo;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
    private String status;

    private boolean activeIndex;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "created_date")
    private Timestamp createdDate;
}
