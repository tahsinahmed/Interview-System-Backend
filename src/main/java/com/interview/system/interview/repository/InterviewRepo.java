package com.interview.system.interview.repository;

import com.interview.system.interview.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepo extends JpaRepository<Interview, Long> {
}
