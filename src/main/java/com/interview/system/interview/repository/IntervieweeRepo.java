package com.interview.system.interview.repository;

import com.interview.system.interview.model.InterViewee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervieweeRepo extends JpaRepository<InterViewee, Long> {
}
