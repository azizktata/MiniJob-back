package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PartnerRepo partnerRepo;


    @GetMapping(value = "/clients")
    public List<Client> getClients(){
        return clientRepo.findAll();
    }

    @PostMapping(value = "/clients")
    public void saveClient(@RequestBody Client C){
        clientRepo.save(C);
    }

    @PutMapping(value = "/clients/img/{id}")
    public void updateImg(@RequestParam("profileImage") MultipartFile file, @PathVariable int id) throws IOException {
        Client updatedC = clientRepo.findById(id).get();
        clientService.uploadImage(file,updatedC);
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable int id) {
        Client C = clientRepo.findById(id).get();
        byte[] image = clientService.downloadImage(C);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @PutMapping(value = "/clients/{clientId}/partner/{partnerId}")
    public Client favoritePartner(
            @PathVariable int clientId,
            @PathVariable int partnerId

    ){
        Client c = clientRepo.findById(clientId).get();
        Partner p = partnerRepo.findById(partnerId).get();

        c.favorPartner(p);
        clientRepo.save(c);
        return c;
    }
    @DeleteMapping(value = "/clients/{clientId}/partner/{partnerId}")
    public Client removeFavorite(
            @PathVariable int clientId,
            @PathVariable int partnerId

    ){
        Client c = clientRepo.findById(clientId).get();
        Partner p = partnerRepo.findById(partnerId).get();

        c.removeFavorie(p);
        clientRepo.save(c);
        return c;
    }





}
