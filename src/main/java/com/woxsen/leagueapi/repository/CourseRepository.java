package com.woxsen.leagueapi.repository;

import com.woxsen.leagueapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByBranch_id(UUID branchId);
}
