package com.example.FlexStaff.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference(value = "client-apply")
    private Client client;

    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    @JsonBackReference (value = "applied-client")
    private Job job;

    @Column(name = "approved")
    private Boolean approved = false;

    public Candidat(Client client, Job job, Boolean approved) {
        this.client = client;
        this.job = job;
        this.approved = approved;
    }

    public void addCandidat(Client c, Job j) {
        this.client = c;
        this.job = j;
    }
}
