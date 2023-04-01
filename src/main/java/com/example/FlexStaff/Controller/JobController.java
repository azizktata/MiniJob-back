package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Job;
import com.example.FlexStaff.Entities.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private PartnerRepo partnerRepo;

    @GetMapping(value = "/jobs")
    public List<Job> getJobs(){
        return jobRepo.findAll();
    }

    @PostMapping(value = "/jobs/addpartner/{partnerId}")
    Job addJob(@RequestBody Job J,@PathVariable int partnerId){
        Partner p = partnerRepo.findById(partnerId).get();
        J.assignPartner(p);
        return jobRepo.save(J);
    }
   @PutMapping(value = "/jobs/{jobId}")
   Job updateJob(@RequestBody Job J, @PathVariable int jobId){
        Job updatedJ = jobRepo.findById(jobId).get();
        updatedJ.setLocation(J.getLocation());
        updatedJ.setDescription(J.getDescription());
        updatedJ.setSalary(J.getSalary());
        updatedJ.setSector(J.getSector());
        updatedJ.setTitle(J.getTitle());
        updatedJ.setWorkTime(J.getWorkTime());
       return jobRepo.save(updatedJ);
   }

    @DeleteMapping(value = "/jobs/{jobId}")
    public String deleteJob(@PathVariable int jobId){
        jobRepo.delete(jobRepo.findById(jobId).get());
        return "Job deleted";
    }

}
