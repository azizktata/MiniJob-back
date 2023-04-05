package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DTO.JobDto;
import com.example.FlexStaff.Entities.Job;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping()
    public List<Job> getJobs(){
        return jobService.getAlljobs();
    }

    @GetMapping(value = "/{jobId}")
    Job getJobById(@PathVariable int jobId){
        return jobService.getById(jobId);
    }

    @PostMapping(value = "/addpartner/{partnerId}")
    int addJob(@RequestBody Job J,@PathVariable int partnerId){
        return jobService.saveJob(partnerId, J);
    }
   @PutMapping(value = "/{jobId}")
   int updateJob(@RequestBody JobDto J, @PathVariable int jobId){
        return jobService.updateJob(J, jobId);
   }

    @DeleteMapping(value = "/{jobId}")
    public String deleteJob(@PathVariable int jobId){
        return jobService.deleteJob(jobId);
    }

}
