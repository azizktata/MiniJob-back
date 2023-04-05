package com.example.FlexStaff.Service;

import com.example.FlexStaff.DAO.CandidatRepo;
import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.Entities.Candidat;
import com.example.FlexStaff.Entities.CandidatKey;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Job;
import com.example.FlexStaff.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CandidatRepo candidatRepo;

    public List<Candidat> getAllCandidats(){return candidatRepo.findAll();}

    public List<Client> getCandidatsPerJob (int jobId){
        return candidatRepo.findByJobId(jobId);
    }
    public List<Job> getJobsPerClient (int clientId){
        return candidatRepo.findByClientId(clientId);
    }
    public CandidatKey applyCandidat(int jobId, int clientId){
        Job j = jobRepo.findById(jobId).orElseThrow(() -> new ObjectNotFoundException("Job not found"));
        Client c = clientRepo.findById(clientId).get();
        CandidatKey key = new CandidatKey(clientId, jobId);
        Candidat CA = new Candidat();
        CA.addCandidat(c,j);
        CA.setId(key);
        return candidatRepo.save(CA).getId();
    }
    public String removeCandidat(int jobId, int clientId){
    //    Job j = jobRepo.findById(jobId).get();
    //    Client c = clientRepo.findById(clientId).get();
    //    CandidatKey key = new CandidatKey(clientId, jobId);
        candidatRepo.delete(candidatRepo.findByKey(clientId, jobId));
        return "candidat: "+clientId+" removed application from "+jobId;
    }
}
