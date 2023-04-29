package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.config.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepoTest {

    @Autowired()
    private ClientRepo clientRepo;


    @Test
    void checkIfClientExistsByEmail() {
        //given
        String email = "aziz@gmail.com";
        Client C = new Client ("Aziz","Ktata",email);
        clientRepo.save(C);
        //when
        var res = clientRepo.findByEmail(email).get();
        //then
        assertEquals(C,res);
    }
}