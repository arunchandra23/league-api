package com.woxsen.leagueapi.entity;

import com.woxsen.leagueapi.utils.GenderTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "arena_slots_gender")
public class ArenaSlotsForGender {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arena_id")
    private Arena arena;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slot_id")
    private Slots slot;

    @Enumerated(EnumType.STRING)
    private GenderTypes gender;
    private String daysForGender;
}
