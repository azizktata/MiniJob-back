package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Candidat;
import com.example.FlexStaff.Entities.CandidatKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CandidatRepo extends JpaRepository<Candidat,Integer> {
    @Query("SELECT c FROM Candidat c " +
            "WHERE c.id.clientId = :clientId " +
            "AND c.id.jobId = :jobId")
    Candidat findByKey(@Param("clientId") int clientId,@Param("jobId") int jobId);

}
