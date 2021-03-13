package com.interview.system.interview.controller;

import com.interview.system.interview.model.Interview;
import com.interview.system.interview.repository.InterviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InterviewController {

    @Autowired
    InterviewRepo interviewRepo;

    @GetMapping("/interview")
    public ResponseEntity<?> getAllInterview() {
        try {
            return ResponseEntity.ok().body(interviewRepo.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Not Responding");
        }
    }

    @PostMapping("/interview")
    public ResponseEntity<?> createInterview(@RequestBody Interview interview) {
       return saveOrUpdateOperation(interview);
    }

    @PutMapping("/interview")
    public ResponseEntity<?> updateInterview(@RequestBody Interview interview) {
        if (interview.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Interview Must Have an ID");
        }
        return saveOrUpdateOperation(interview);
    }

    @GetMapping("/interview/{id}")
    public ResponseEntity<?> getInterviewById(@PathVariable(name = "id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Interview ID cannot be null");
            }
            Optional<Interview> result = interviewRepo.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.ok().body(interviewRepo.findById(id));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Details are not found for id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Not Responding");
        }
    }

    private ResponseEntity<?> saveOrUpdateOperation(Interview interview) {
        try {
            return ResponseEntity.ok().body(interviewRepo.save(interview));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Not Responding");
        }
    }
}
