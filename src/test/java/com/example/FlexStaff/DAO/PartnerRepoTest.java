package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Job;
import com.example.FlexStaff.Entities.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PartnerRepoTest {
    @Autowired
    private PartnerRepo partnerRepo;
    @Autowired
    private JobRepo jobRepo;

    @Test
    void shouldfindJobsByPartnerId() {
        Partner P = new Partner("magasin","Tunis");
        var savedP = partnerRepo.save(P);

        Job J = new Job("Traiteur","description",50);
        J.assignPartner(savedP);
        jobRepo.save(J);

        //when
        var jobs = partnerRepo.findJobsByPartnerId(savedP.getIdP());
        boolean res = jobs.contains(J);

        //then
        assertEquals(res,true);





    }
}