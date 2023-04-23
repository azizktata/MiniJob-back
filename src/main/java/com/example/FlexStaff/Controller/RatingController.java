package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DTO.RateDto;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Entities.PartnerRating;
import com.example.FlexStaff.Entities.PartnerRatingKey;
import com.example.FlexStaff.Service.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping()
    public List<PartnerRating> getRating(){
        return ratingService.getAllRatings();
    }

    @GetMapping(value = "/partner/{partnerId}")
    public double getRatingByPartner(@PathVariable int partnerId){
        return ratingService.getRatingByPartner(partnerId);
    }

    @PostMapping(value = "/client/{clientId}/partner/{partnerId}")
    public PartnerRatingKey postRating(
            @RequestBody RateDto stars,
            @PathVariable int clientId,
            @PathVariable int partnerId)
    {
        return ratingService.ratePartner(clientId, partnerId, stars);
    }


}
