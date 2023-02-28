package com.woxsen.leagueapi.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woxsen.leagueapi.utils.ArenaTypes;
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
public class Arena {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID id;

    private String name;

    @Column(name = "desc")
    private String description;

    @Enumerated(EnumType.STRING)
    private ArenaTypes arenaType;

//    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "arena_slots",
            joinColumns = @JoinColumn(name = "arena_id"),
            inverseJoinColumns = @JoinColumn(name = "slot_id",referencedColumnName = "id")
    )
    private List<Slots> slots;

    @JsonIgnore
    @OneToMany(mappedBy = "arena")
    private List<Bookings> bookings;

    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean activeIndex;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "created_date")
    private Timestamp createdDate;
}
