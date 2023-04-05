package com.example.FlexStaff.Service;


import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.JobDto;
import com.example.FlexStaff.Entities.Job;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private PartnerRepo partnerRepo;


    public List<Job> getAlljobs (){
        return jobRepo.findAll();
    }

    public int saveJob(int partnerId, Job J) {
        Partner p = partnerRepo.findById(partnerId).get();
        J.assignPartner(p);
        return jobRepo.save(J).getIdJ();
    }

    public int updateJob(JobDto J, int jobId){
        Job updatedJ = jobRepo.findById(jobId).get();
        updatedJ.setLocation(J.getLocation());
        updatedJ.setDescription(J.getDescription());
        updatedJ.setSalary(J.getSalary());
        updatedJ.setSector(J.getSector());
        updatedJ.setTitle(J.getTitle());
        updatedJ.setWorkTime(J.getWorkTime());
        return jobRepo.save(updatedJ).getIdJ();
    }

    public String deleteJob(int jobId){
        jobRepo.delete(jobRepo.findById(jobId).get());
        return "job : "+ jobId + "is deleted";
    }


    public Job getById(int jobId) {
        return jobRepo.findById(jobId).orElseThrow(()->new ObjectNotFoundException("Job not found"));
    }
}
