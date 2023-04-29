package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Entities.PartnerRating;
import com.example.FlexStaff.Entities.PartnerRatingKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartnerRatingRepoTest {

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PartnerRepo partnerRepo;
    @Autowired
    private PartnerRatingRepo partnerRatingRepo;
    @Test
    void shouldReturnMoyRate(){
        //given
        Client C = new Client("Aziz","Ktata","aziz@gmail.com");
        var savedC = clientRepo.save(C);
        Partner P = new Partner("magasin","Tunis");
        var savedP = partnerRepo.save(P);
        int stars = 4;
        PartnerRating PR = new PartnerRating(stars);
        PR.addRate(C,P);
        PartnerRatingKey key = new PartnerRatingKey(savedC.getIdC(), savedP.getIdP());
        PR.setId(key);
        partnerRatingRepo.save(PR);

        Client C2 = new Client("Aziz2","Ktata2","aziz@gmail.com");
        var savedC2 = clientRepo.save(C2);
        int stars2 = 2;
        PartnerRating PR2 = new PartnerRating(stars2);
        PR2.addRate(C2,P);
        PartnerRatingKey key2 = new PartnerRatingKey(savedC2.getIdC(), savedP.getIdP());
        PR2.setId(key2);
        partnerRatingRepo.save(PR2);

        //when
        var res = partnerRatingRepo.getMoyRate(savedP.getIdP());

        //then
        assertEquals(3.0,res);
    }

}