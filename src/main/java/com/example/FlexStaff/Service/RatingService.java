package com.example.FlexStaff.Service;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.PartnerRatingRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.RateDto;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Entities.PartnerRating;
import com.example.FlexStaff.Entities.PartnerRatingKey;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RatingService {
    private PartnerRatingRepo ratingRepo;

    private ClientRepo clientRepo;

    private PartnerRepo partnerRepo;

    public List<PartnerRating> getAllRatings (){return ratingRepo.findAll();}

    public PartnerRatingKey ratePartner (int clientId, int partnerId, RateDto R){
        Partner p = partnerRepo.findById(partnerId).get();
        Client c = clientRepo.findById(clientId).get();
        PartnerRating PR = new PartnerRating(R.getStars());
        PR.addRate(c,p);
        PartnerRatingKey key = new PartnerRatingKey(clientId, partnerId);
        PR.setId(key);
        return ratingRepo.save(PR).getId();
    }

    public double getRatingByPartner(int partnerId) {
        return ratingRepo.getMoyRate(partnerId);
    }
}
