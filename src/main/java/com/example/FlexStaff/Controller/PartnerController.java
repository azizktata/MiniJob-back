package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.CandidatDto;
import com.example.FlexStaff.Entities.*;
import com.example.FlexStaff.Entities.Enum.Status;
import com.example.FlexStaff.Service.PartnerService;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/partners")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping()
    public List<Partner> getPartnerss(){
        return partnerService.getAllPartners();
    }

    @PostMapping()
    public int addPartner(@RequestBody Partner p){
        return partnerService.savePartner(p);
    }

    @DeleteMapping(value = "/{partnerId}")
    public String deletePartner(@PathVariable int partnerId){
        return partnerService.removePartner(partnerId);
    }

    @PutMapping(value = "/candidats/client/{clientId}/job/{jobId}/{code}")
    Candidat manageCandidat(
            @PathVariable int clientId,
            @PathVariable int jobId,
            @PathVariable String code
            )
    {
       return partnerService.manageCandidat(clientId, jobId, code);
    }
    /* @PutMapping(value = "/partners/{partnerId}/jobs/{jobId}")
    Partner addJob(@PathVariable int partnerId, @PathVariable int jobId){
        Partner p = partnerRepo.findById(partnerId).get();
        Job j = jobRepo.findById(jobId).get();
        p.addJob(j);
        return partnerRepo.save(p);

    }*/
}
