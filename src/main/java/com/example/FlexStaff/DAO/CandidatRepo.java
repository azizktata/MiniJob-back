package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Candidat;
import com.example.FlexStaff.Entities.CandidatKey;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CandidatRepo extends JpaRepository<Candidat,Integer> {
    @Query("SELECT c FROM Candidat c " +
            "WHERE c.id.clientId = :clientId " +
            "AND c.id.jobId = :jobId")
    Candidat findByKey(@Param("clientId") int clientId,@Param("jobId") int jobId);

    @Query("SELECT cl FROM Client cl, Candidat c " +
            "WHERE c.id.clientId = cl.id " +
            "AND c.id.jobId = :jobId ")
    List<Client> findByJobId(@Param("jobId") int jobId);

    @Query("SELECT j FROM Job j, Candidat c " +
            "WHERE c.id.jobId = j.id " +
            "AND c.id.clientId = :clientId ")
    List<Job> findByClientId(int clientId);
}
