package com.interview.system.interview.controller;

import com.interview.system.interview.model.InterViewee;
import com.interview.system.interview.repository.IntervieweeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class IntervieweeController {

    @Autowired
    IntervieweeRepo intervieweeRepo;

    @GetMapping("/interviewee")
    public ResponseEntity<?> getAllInterviewee() {
        return ResponseEntity.ok().body(intervieweeRepo.findAll());
    }

    @PostMapping("/interviewee")
    public ResponseEntity<?> createInterviewee(@RequestBody InterViewee interViewee) throws NoSuchAlgorithmException {
        SecureRandom r = SecureRandom.getInstance("SHA1PRNG");
        int random = r.nextInt(999999);
        interViewee.setIntervieweeId(String.format("%06d", random));
        return saveOrUpdateOperation(interViewee);
    }

    @PutMapping("/interviewee")
    public ResponseEntity<?> updateInterviewee(@RequestBody InterViewee interViewee) {
        if (interViewee.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Interviewee must have an ID");
        }
        return saveOrUpdateOperation(interViewee);
    }

    @GetMapping("/interviewee/{id}")
    public ResponseEntity<?> getIntervieweeById(@PathVariable(name = "id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Interviewee ID cannot be null");
            }
            Optional<InterViewee> interViewee = intervieweeRepo.findById(id);
            if (interViewee.isPresent()) {
                return ResponseEntity.ok().body(interViewee.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Details are not found for id: " + id);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Save Operation was Not Successful");
        }
    }

    private ResponseEntity<?> saveOrUpdateOperation(InterViewee interViewee) {
        try {
            return ResponseEntity.ok().body(intervieweeRepo.save(interViewee));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Save Operation was Not Successful");
        }
    }
}
