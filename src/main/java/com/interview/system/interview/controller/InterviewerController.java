package com.interview.system.interview.controller;

import com.interview.system.interview.model.InterViewee;
import com.interview.system.interview.model.Interviewer;
import com.interview.system.interview.repository.InterviewerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/interviewer/{id}")
    public ResponseEntity<?> getInterviewerById(@PathVariable(name = "id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Interviewer ID cannot be null");
            }
            Optional<Interviewer> interViewer = interviewerRepo.findById(id);
            if (interViewer.isPresent()) {
                return ResponseEntity.ok().body(interViewer.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Details are not found for id: " + id);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Save Operation was Not Successful");
        }
    }

    private ResponseEntity<?> saveOrUpdateOperation(Interviewer interviewer) {
        try {
            return ResponseEntity.ok().body(interviewerRepo.save(interviewer));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Not Responding");
        }
    }
}
