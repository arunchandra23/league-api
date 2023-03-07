package com.woxsen.leagueapi.entity;

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
public class SecurityQuestions {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID id;

	private String question;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private Status status;
	@JsonIgnore
	private boolean activeIndex;
	@JsonIgnore
	@CreationTimestamp
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "created_date")
	private Timestamp createdDate;

}