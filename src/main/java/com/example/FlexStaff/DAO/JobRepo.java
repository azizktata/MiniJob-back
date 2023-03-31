package com.example.FlexStaff.DAO;

import com.example.FlexStaff.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JobRepo extends JpaRepository<Job,Integer> {
}
