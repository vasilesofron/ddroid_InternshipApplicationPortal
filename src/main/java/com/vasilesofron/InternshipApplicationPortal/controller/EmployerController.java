package com.vasilesofron.InternshipApplicationPortal.controller;

import com.vasilesofron.InternshipApplicationPortal.model.Employer;
import com.vasilesofron.InternshipApplicationPortal.model.JobListing;
import com.vasilesofron.InternshipApplicationPortal.service.EmployerService;
import com.vasilesofron.InternshipApplicationPortal.service.JobListingService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employers")
public class EmployerController {

    @Resource
    private EmployerService employerService;
    @Resource
    private JobListingService jobListingService;

    @GetMapping
    public ResponseEntity<?> getAllEmployers(){
        return new ResponseEntity<>(employerService.getAllEmployers(), HttpStatus.OK);
    }

    @PostMapping("/create-employer")
    public ResponseEntity<?> createEmployer(@RequestBody Employer employer){
        return new ResponseEntity<>(employerService.createEmployer(employer), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-employer/{employerId}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long employerId){
        try {
            employerService.deleteEmployerById(employerId);
            return ResponseEntity.ok("Employer deleted successfully.");
        } catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employer found.");
        }
    }

    /*@PostMapping ("/create-new-job-listing/{employerId}")
    public ResponseEntity<?> createNewJobListing(@PathVariable Long employerId, @RequestBody JobListing jobListing){
        return  new ResponseEntity<>(jobListingService.createJobListing(employerId, jobListing), HttpStatus.CREATED);
    }
    */
}
