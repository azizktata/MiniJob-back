package com.example.FlexStaff.Service;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.ClientDto;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Exceptions.ObjectNotFoundException;
import com.example.FlexStaff.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ClientService implements UserDetailsService {

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
        Client c = clientRepo.findById(clientId).orElseThrow(() -> new ObjectNotFoundException("Client not found"));
        Partner p = partnerRepo.findById(partnerId).orElseThrow(()-> new ObjectNotFoundException("Partner not found"));

        c.favorPartner(p);
        return clientRepo.save(c);
    }
    public Client removeFavoris(int clientId, int partnerId){
        Client c = clientRepo.findById(clientId).orElseThrow(() -> new ObjectNotFoundException("Client not found"));
        Partner p = partnerRepo.findById(partnerId).orElseThrow(() -> new ObjectNotFoundException("Partner not found"));

        c.removeFavorie(p);
        return clientRepo.save(c);

    }
    public int updateClient(ClientDto C, int clientId){

        Client updatedC = clientRepo.findById(clientId).orElseThrow(() -> new ObjectNotFoundException("Client not found"));

        updatedC.setFirstName(C.getFirstName());
        updatedC.setLastName(C.getLastName());
        updatedC.setEmail(C.getEmail());
        updatedC.setBirth(C.getBirth());
        updatedC.setCity(C.getCity());
        updatedC.setPassword(C.getPassword());
        return clientRepo.save(updatedC).getIdC();
    }

    @Override
    public Client loadUserByUsername(String username) throws UsernameNotFoundException {
        return  clientRepo.findByEmail(username).get();
    }

    public void remove(int clientId) {
        Client C = clientRepo.findById(clientId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        clientRepo.delete(C);
    }
}
