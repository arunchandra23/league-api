package com.arun.springboot.jpaCourse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course-material")
public class CourseMaterial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long courseMaterialId;
	private String url;
	
	@OneToOne
	@JoinColumn(name="course_id",referencedColumnName = "courseId",unique = true)
	private Course course;

}
