package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Candidat;
import com.example.FlexStaff.Entities.CandidatKey;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Enum.Status;
import com.example.FlexStaff.Entities.Job;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CandidatRepoTest {

    private Client C;
    private Job J;

    @Autowired
    private CandidatRepo candidatRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private ClientRepo clientRepo;

    @AfterEach
    void tearDown() {
        clientRepo.deleteAll();
        jobRepo.deleteAll();
        candidatRepo.deleteAll();
    }

    @BeforeEach
    void setUp() {
        C = new Client("Aziz","Ktata","aziz@gmail.com");
        clientRepo.save(C);
        J = new Job("Traiteur","description",50);
        jobRepo.save(J);
    }


    @Test
    void shouldReturnCandidatByKey() {
        //given

        Candidat Cand = new Candidat(C,J, Status.Approved);
        CandidatKey key = new CandidatKey(C.getIdC(),J.getIdJ());
        Cand.setId(key);
        var savedCand = candidatRepo.save(Cand);

        //when
        var res = candidatRepo.findByKey(C.getIdC(),J.getIdJ());

        //then
        assertEquals(savedCand, res);


    }

    @Test
    void shouldReturnClientsThatApplyForJobByJobId() {


        Client C2 = new Client("Aziz2","Ktata2","aziz2@gmail.com");
        var idC2 = clientRepo.save(C2).getIdC();

        Candidat Cand = new Candidat(C,J,Status.inProcess);
        CandidatKey key1 = new CandidatKey(C.getIdC(),J.getIdJ());
        Cand.setId(key1);
        candidatRepo.save(Cand);

        Candidat Cand2 = new Candidat(C2,J,Status.inProcess);
        CandidatKey key2 = new CandidatKey(idC2,J.getIdJ());
        Cand2.setId(key2);
        candidatRepo.save(Cand2);

        //when
        var appliedClients = candidatRepo.findByJobId(J.getIdJ());
        List<Client> expected = new ArrayList<>();
        expected.add(C);
        expected.add(C2);
        //then
        assertArrayEquals(expected.toArray(), appliedClients.toArray());

    }

    @Test
    void findCandidatsByJobId() {
        //given
        Candidat Cand = new Candidat(C,J,Status.inProcess);
        CandidatKey key1 = new CandidatKey(C.getIdC(),J.getIdJ());
        Cand.setId(key1);
        candidatRepo.save(Cand);

        Client C2 = new Client("Aziz2","Ktata2","aziz2@gmail.com");
        var savedC = clientRepo.save(C2);

        Candidat Cand2 = new Candidat(C2,J,Status.inProcess);
        CandidatKey key2 = new CandidatKey(savedC.getIdC(),J.getIdJ());
        Cand2.setId(key2);
        candidatRepo.save(Cand2);

        //when
        var candidats = candidatRepo.findCandidatsByJobId(J.getIdJ());
        List<Candidat> expected = new ArrayList<>();
        expected.add(Cand);
        expected.add(Cand2);

        //then
        assertArrayEquals(expected.toArray(),candidats.toArray());
    }

    @Test
    void findByClientId() {
        //given
        Candidat Cand = new Candidat(C,J,Status.inProcess);
        CandidatKey key1 = new CandidatKey(C.getIdC(),J.getIdJ());
        Cand.setId(key1);
        candidatRepo.save(Cand);

        Job J2 = new Job("Livreur","description",30);
        var savedJ = jobRepo.save(J2);

        Candidat Cand2 = new Candidat(C,J2,Status.inProcess);
        CandidatKey key2 = new CandidatKey(C.getIdC(),savedJ.getIdJ());
        Cand2.setId(key2);
        candidatRepo.save(Cand2);

        //when
        var jobs = candidatRepo.findByClientId(C.getIdC());
        List<Job> expected = new ArrayList<>();
        expected.add(J);
        expected.add(savedJ);

        //then
        assertArrayEquals(expected.toArray(), jobs.toArray());

    }
}