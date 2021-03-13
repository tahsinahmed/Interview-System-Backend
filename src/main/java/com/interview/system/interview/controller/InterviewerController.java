package com.interview.system.interview.controller;

import com.interview.system.interview.model.Interviewer;
import com.interview.system.interview.repository.InterviewerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InterviewerController {

    @Autowired
    InterviewerRepo interviewerRepo;

    @GetMapping("/interviewer")
    public ResponseEntity<?> getAllInterviewer() {
        try {
            return ResponseEntity.ok().body(interviewerRepo.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Not Responding");
        }
    }


    @PostMapping("/interviewer")
    public ResponseEntity<?> createInterviewer(@RequestBody Interviewer interviewer) {
        return saveOrUpdateOperation(interviewer);
    }

    @PutMapping("/interviewer")
    public ResponseEntity<?> updateInterviewer(@RequestBody Interviewer interviewer) {
        return saveOrUpdateOperation(interviewer);
    }

    private ResponseEntity<?> saveOrUpdateOperation(Interviewer interviewer) {
        try {
            return ResponseEntity.ok().body(interviewerRepo.save(interviewer));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Not Responding");
        }
    }
}
