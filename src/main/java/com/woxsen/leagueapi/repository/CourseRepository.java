package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findBySchool_id(UUID schoolId);
}
