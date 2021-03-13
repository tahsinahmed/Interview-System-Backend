package com.interview.system.interview.repository;

import com.interview.system.interview.model.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerRepo extends JpaRepository<Interviewer, Long> {
}
