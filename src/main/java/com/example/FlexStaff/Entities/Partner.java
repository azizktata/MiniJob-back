package com.example.FlexStaff.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Partner extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idP")
    private int idP;

    @NonNull
    @Column(name = "businessName")
    private String businessName;

    @Column(name = "description")
    private String description;

    @Column(name = "businessLocation")
    private String businessLocation;

    @Column(name = "sector")
    private String sector;

    @Lob
    @Column(name = "profileImgData")
    private byte[] profileImgData;

    @Column(name = "profileImgName")
    private String profileImgName;

    @OneToMany (mappedBy = "partner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Job> jobs;

    @OneToMany(mappedBy = "partner")
    private List<PartnerRating> ratings;

    @ManyToMany (mappedBy = "favorites")
    @JsonIgnore
    private List<Client> favoredBy;

    public Partner(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password, String region, @NonNull String businessName, String description, String businessLocation, String sector) {
        super(firstName, lastName, email, password, region);
        this.businessName = businessName;
        this.description = description;
        this.businessLocation = businessLocation;
        this.sector = sector;
    }

    public Partner(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password, String region, @NonNull String businessName, String description, String businessLocation, String sector, List<Job> jobs) {
        super(firstName, lastName, email, password, region);
        this.businessName = businessName;
        this.description = description;
        this.businessLocation = businessLocation;
        this.sector = sector;
        this.jobs = jobs;
    }

    public void addJob(Job j) {
        jobs.add(j);
    }
}
