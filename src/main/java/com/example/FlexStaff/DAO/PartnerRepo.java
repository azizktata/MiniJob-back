package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface PartnerRepo extends JpaRepository<Partner,Integer> {
    Optional<Partner> findByEmail(String username);
}
