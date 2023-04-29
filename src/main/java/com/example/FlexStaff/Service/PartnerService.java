package com.example.FlexStaff.Service;

import com.example.FlexStaff.DAO.CandidatRepo;
import com.example.FlexStaff.DAO.JobRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.CandidatDto;
import com.example.FlexStaff.Entities.*;
import com.example.FlexStaff.Entities.Enum.Status;
import com.example.FlexStaff.Exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Service
@AllArgsConstructor
public class PartnerService implements UserDetailsService {

    private PartnerRepo partnerRepo;



    public List<Partner> getAllPartners (){
        return partnerRepo.findAll();
    }

    public Partner getById (int partnerId){
        return partnerRepo.findById(partnerId).orElseThrow(()-> new ObjectNotFoundException("Partner not found"));
    }

    public List<Job> getJobsPerPartner(int partnerId){
        return partnerRepo.findJobsByPartnerId(partnerId);
    }


    public Partner savePartner(Partner P){
        return partnerRepo.save(P);
    }

    public String removePartner (int partnerId){
        partnerRepo.delete(partnerRepo.findById(partnerId).get());
        return "Partner : "+partnerId+" has been deleted";
    }

    public Partner loadUserByUsername(String username) throws UsernameNotFoundException{
        return  partnerRepo.findByEmail(username).get();


    }
}
