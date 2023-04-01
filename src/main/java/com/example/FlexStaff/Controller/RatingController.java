package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.DAO.PartnerRatingRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Entities.PartnerRating;
import com.example.FlexStaff.Entities.PartnerRatingKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {
    @Autowired
    private PartnerRatingRepo ratingRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PartnerRepo partnerRepo;

    @GetMapping(value = "/ratings")
    public List<PartnerRating> getRating(){
        return ratingRepo.findAll();
    }

    @PostMapping(value = "/ratings/client/{clientId}/partner/{partnerId}")
    public PartnerRating postRating(
            @RequestBody PartnerRating PR,
            @PathVariable int clientId,
            @PathVariable int partnerId){
        Partner p = partnerRepo.findById(partnerId).get();
        Client c = clientRepo.findById(clientId).get();
        PR.addRate(c,p);
        PartnerRatingKey key = new PartnerRatingKey(clientId, partnerId);
        PR.setId(key);
        return ratingRepo.save(PR);


    }

   /* @PutMapping(value = "/partners/{partnerId}/jobs/{jobId}")
    Partner addJob(@PathVariable int partnerId, @PathVariable int jobId){
        Partner p = partnerRepo.findById(partnerId).get();
        Job j = jobRepo.findById(jobId).get();
        p.addJob(j);
        return partnerRepo.save(p);

    }*/
}
