package com.example.FlexStaff.Controller;

import com.example.FlexStaff.Entities.*;
import com.example.FlexStaff.Service.CandidatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/candidats")
public class CandidatController {

    private final CandidatService candidatService;

    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    @GetMapping()
    public List<Candidat> getCandidats(){
        return candidatService.getAllCandidats();
    }

    @GetMapping(value = "/job/{jobId}")
    public List<Client> getCandidatsPerJob(@PathVariable int jobId){

        return candidatService.getCandidatsPerJob(jobId);
    }

    @GetMapping(value = "/client/{clientId}")
    public List<Job> getJobsPerClient(@PathVariable int clientId){

        return candidatService.getJobsPerClient(clientId);
    }

    @PostMapping(value = "/client/{clientId}/job/{jobId}")
    public CandidatKey postCandidat(
            @PathVariable int clientId,
            @PathVariable int jobId)
    {
        return candidatService.applyCandidat(clientId, jobId);
    }
    @DeleteMapping(value = "/client/{clientId}/job/{jobId}")
    public String deleteCandidat(
            @PathVariable int clientId,
            @PathVariable int jobId)
    {
        return candidatService.removeCandidat(clientId, jobId);
    }


    @PutMapping(value = "/client/{clientId}/job/{jobId}/{code}")
    Candidat manageCandidat(
            @PathVariable int clientId,
            @PathVariable int jobId,
            @PathVariable String code
    )
    {
        return candidatService.manageCandidat(clientId, jobId, code);
    }
   /* @PutMapping(value = "/partners/{partnerId}/jobs/{jobId}")
    Partner addJob(@PathVariable int partnerId, @PathVariable int jobId){
        Partner p = partnerRepo.findById(partnerId).get();
        Job j = jobRepo.findById(jobId).get();
        p.addJob(j);
        return partnerRepo.save(p);

    }*/
}
