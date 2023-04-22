package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.CandidatDto;
import com.example.FlexStaff.DTO.PartnerDto;
import com.example.FlexStaff.Entities.*;
import com.example.FlexStaff.Entities.Enum.Status;
import com.example.FlexStaff.Service.PartnerService;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/partners")
public class PartnerController {

    private final PartnerService partnerService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping()
    public List<Partner> getPartnerss(){
        return partnerService.getAllPartners();
    }

    @GetMapping(value = "/{partnerId}")
    public Partner getPartnerById(@PathVariable int partnerId){
        return partnerService.getById(partnerId);
    }

    @PostMapping()
    public int addPartner(@RequestBody Partner p){
        return partnerService.savePartner(p).getIdP();
    }

    @DeleteMapping(value = "/{partnerId}")
    public String deletePartner(@PathVariable int partnerId){
        return partnerService.removePartner(partnerId);
    }

    @PutMapping(value = "/{partnerId}")
    public Partner updatePartner(@PathVariable int partnerId, @RequestBody PartnerDto P){
        Partner updatedPartner = partnerService.getById(partnerId);
        updatedPartner.setFirstName(P.getFirstName());
        updatedPartner.setLastName(P.getLastName());
        updatedPartner.setEmail(P.getEmail());
        updatedPartner.setPassword(passwordEncoder.encode(P.getPassword()));
        updatedPartner.setBusinessName(P.getBusinessName());
        updatedPartner.setDescription(P.getDescription());
        updatedPartner.setBusinessLocation(P.getBusinessLocation());
        return partnerService.savePartner(updatedPartner);
    }


    /* @PutMapping(value = "/partners/{partnerId}/jobs/{jobId}")
    Partner addJob(@PathVariable int partnerId, @PathVariable int jobId){
        Partner p = partnerRepo.findById(partnerId).get();
        Job j = jobRepo.findById(jobId).get();
        p.addJob(j);
        return partnerRepo.save(p);

    }*/
}
