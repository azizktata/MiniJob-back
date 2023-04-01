package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.*;
import com.example.FlexStaff.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidatController {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CandidatRepo candidatRepo;

    @GetMapping(value = "/candidats")
    public List<Candidat> getCandidats(){
        return candidatRepo.findAll();
    }

    @PostMapping(value = "/candidats/client/{clientId}/job/{jobId}")
    public Candidat postCandidat(
            @RequestBody Candidat CA,
            @PathVariable int clientId,
            @PathVariable int jobId){
        Job j = jobRepo.findById(jobId).get();
        Client c = clientRepo.findById(clientId).get();
        CandidatKey key = new CandidatKey(clientId, jobId);
        CA.addCandidat(c,j);

        CA.setId(key);
        return candidatRepo.save(CA);


    }
    @DeleteMapping(value = "/candidats/client/{clientId}/job/{jobId}")
    public String deleteCandidat(
            @PathVariable int clientId,
            @PathVariable int jobId){
        Job j = jobRepo.findById(jobId).get();
        Client c = clientRepo.findById(clientId).get();
        CandidatKey key = new CandidatKey(clientId, jobId);

       candidatRepo.delete(candidatRepo.findByKey(clientId, jobId));
        return "candidat deleted";

    }

   /* @PutMapping(value = "/partners/{partnerId}/jobs/{jobId}")
    Partner addJob(@PathVariable int partnerId, @PathVariable int jobId){
        Partner p = partnerRepo.findById(partnerId).get();
        Job j = jobRepo.findById(jobId).get();
        p.addJob(j);
        return partnerRepo.save(p);

    }*/
}
