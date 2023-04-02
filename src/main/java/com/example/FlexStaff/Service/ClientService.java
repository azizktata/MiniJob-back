package com.example.FlexStaff.Service;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.ClientDto;
import com.example.FlexStaff.DTO.JobDto;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Job;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PartnerRepo partnerRepo;

    public Client uploadImage(MultipartFile file, int clientId) throws IOException {

        Client updatedC = clientRepo.findById(clientId).get();
        updatedC.setProfileImgName(file.getOriginalFilename());

        updatedC.setProfileImgData(ImageUtil.compressImage(file.getBytes()));
        return clientRepo.save(updatedC);
    }

    public byte[] downloadImage(int  clientId){
        Client C = clientRepo.findById(clientId).get();
        return ImageUtil.decompressImage(C.getProfileImgData());
    }

    public List<Client> getAllClients (){
        return clientRepo.findAll();
    }

    public int saveClient(Client C){
        return clientRepo.save(C).getIdC();
    }

    public Client addFavoris(int clientId, int partnerId){
        Client c = clientRepo.findById(clientId).get();
        Partner p = partnerRepo.findById(partnerId).get();

        c.favorPartner(p);
        return clientRepo.save(c);
    }
    public Client removeFavoris(int clientId, int partnerId){
        Client c = clientRepo.findById(clientId).get();
        Partner p = partnerRepo.findById(partnerId).get();

        c.removeFavorie(p);
        return clientRepo.save(c);

    }
    public int updateClient(ClientDto C, int clientId){
        Client updatedC = clientRepo.findById(clientId).get();
        updatedC.setFirstName(C.getFirstName());
        updatedC.setLastName(C.getLastName());
        updatedC.setEmail(C.getEmail());
        updatedC.setBirth(C.getBirth());
        updatedC.setCity(C.getCity());
        updatedC.setPassword(C.getPassword());
        return clientRepo.save(updatedC).getIdC();
    }

}
