package com.example.FlexStaff.Controller;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.DTO.ClientDto;
import com.example.FlexStaff.DTO.JobDto;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Exceptions.ObjectNotFoundException;
import com.example.FlexStaff.Service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;



    @GetMapping()
    public List<Client> getClients(){
        return clientService.getAllClients();
    }

    @GetMapping(value = "/{clientId}")
    public Client getClientById(@PathVariable int clientId){
        return clientService.getClientById(clientId);
    }
    /*@GetMapping(value = "/{email}")
    public UserDetails getClientsE(@PathVariable String email){
        return clientService.loadUserByUsername(email);
    }*/

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
    int updateClient(@RequestBody ClientDto C, @PathVariable int clientId){
        Client updatedC = clientService.getClientById(clientId);
        updatedC.setFirstName(C.getFirstName());
        updatedC.setLastName(C.getLastName());
        updatedC.setEmail(C.getEmail());
        updatedC.setBirth(C.getBirth());
        updatedC.setCity(C.getCity());
        updatedC.setPassword(passwordEncoder.encode(C.getPassword()));
        return clientService.saveClient(updatedC);
    }
    @DeleteMapping(value = "/{clientId}")
     public String deleteClient(@PathVariable int clientId){

        clientService.remove(clientId);
        return "client "+clientId+" deleted";
    }





}
