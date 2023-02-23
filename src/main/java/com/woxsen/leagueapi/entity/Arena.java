package com.woxsen.leagueapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woxsen.leagueapi.utils.ArenaTypes;
import com.woxsen.leagueapi.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "arena_slots",
            joinColumns = @JoinColumn(name = "arena_id"),
            inverseJoinColumns = @JoinColumn(name = "slot_id",referencedColumnName = "id")
    )
    private List<Slots> slots;

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
