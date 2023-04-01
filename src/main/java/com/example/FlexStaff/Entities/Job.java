package com.example.FlexStaff.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJ")
    private int idJ ;

    @Column(name = "title")
    private String title ;

    @Column(name = "description")
    private String description ;

    @Column(name = "sector")
    private String sector ;

    @Column(name = "workTime")
    private String workTime ;

    @Column(name = "salary")
    private long salary ;

    @Column(name = "location")
    private String location ;


    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id", referencedColumnName = "idP")
    @JsonBackReference (value = "partner-jobs")
    private Partner partner;

    @OneToMany(mappedBy = "job")
    @JsonManagedReference(value = "applied-client")
    private List<Candidat> appliedClients;

    public Job(String title, String description, String sector, String workTime, long salary, String location) {
        this.title = title;
        this.description = description;
        this.sector = sector;
        this.workTime = workTime;
        this.salary = salary;
        this.location = location;
    }

    public void assignPartner(Partner p) {
        this.partner = p;
    }
}
