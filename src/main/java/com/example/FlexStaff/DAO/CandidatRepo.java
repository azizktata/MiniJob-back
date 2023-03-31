package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CandidatRepo extends JpaRepository<Candidat,Integer> {
}
