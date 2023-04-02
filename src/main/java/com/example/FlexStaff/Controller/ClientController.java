package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.ClientDto;
import com.example.FlexStaff.DTO.JobDto;
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
@RequestMapping(value = "api/v1/clients")
public class ClientController {
    @Autowired
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public List<Client> getClients(){
        return clientService.getAllClients();
    }

    @PostMapping()
    public int addClient(@RequestBody Client C){
        return clientService.saveClient(C);
    }

    @PutMapping(value = "/upload/{clientId}")
    public void updateImg(@RequestParam("profileImage") MultipartFile file, @PathVariable int clientId) throws IOException {

        clientService.uploadImage(file,clientId);
    }
    @GetMapping("/download/{clientId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable int clientId) {

        byte[] image = clientService.downloadImage(clientId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @PutMapping(value = "/{clientId}/partner/{partnerId}")
    public Client favoritePartner(
            @PathVariable int clientId,
            @PathVariable int partnerId
    ){
        return clientService.addFavoris(clientId, partnerId);
    }
    @DeleteMapping(value = "/{clientId}/partner/{partnerId}")
    public Client removeFromFavorite(
            @PathVariable int clientId,
            @PathVariable int partnerId
    ){
        return clientService.removeFavoris(clientId, partnerId);
    }

    @PutMapping(value = "/{clientId}")
    int updateJob(@RequestBody ClientDto C, @PathVariable int clientId){
        return clientService.updateClient(C, clientId);
    }





}
