package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.PartnerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartnerRatingRepo extends JpaRepository<PartnerRating,Integer> {

    @Query("Select AVG(R.stars) from PartnerRating R where R.id.partnerId = :partnerId")
    double getMoyRate(@Param("partnerId") int partnerId);
}
