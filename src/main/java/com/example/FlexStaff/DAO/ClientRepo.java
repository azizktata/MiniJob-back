package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ClientRepo extends JpaRepository<Client,Integer> {
    Optional<Client> findByProfileImgName (String filename);
    Optional<Client> findByEmail(String email);
}
