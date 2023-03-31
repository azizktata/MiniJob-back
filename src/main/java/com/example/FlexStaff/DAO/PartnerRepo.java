package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartnerRepo extends JpaRepository<Partner,Integer> {
}
