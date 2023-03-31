package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.PartnerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartnerRatingRepo extends JpaRepository<PartnerRating,Integer> {
}
