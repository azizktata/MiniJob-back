package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Job;
import com.example.FlexStaff.Entities.Partner;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PartnerController {
    @Autowired
    private PartnerRepo partnerRepo;

    @Autowired
    private JobRepo jobRepo;

    @GetMapping(value = "/partners")
    public List<Partner> getPartnerss(){
        return partnerRepo.findAll();
    }

    @PostMapping(value = "/partners")
    public Partner postPartner(@RequestBody Partner p){
        return partnerRepo.save(p);
    }

   /* @PutMapping(value = "/partners/{partnerId}/jobs/{jobId}")
    Partner addJob(@PathVariable int partnerId, @PathVariable int jobId){
        Partner p = partnerRepo.findById(partnerId).get();
        Job j = jobRepo.findById(jobId).get();
        p.addJob(j);
        return partnerRepo.save(p);

    }*/
}
