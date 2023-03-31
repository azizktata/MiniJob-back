package com.example.FlexStaff.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Candidat {
    @EmbeddedId
    private CandidatKey id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "status")
    private Boolean status = false;

    public Candidat(Client client, Job job, Boolean status) {
        this.client = client;
        this.job = job;
        this.status = status;
    }
}
