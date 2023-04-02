package com.example.FlexStaff.Entities;

import com.example.FlexStaff.Entities.Enum.Status;
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

    @Column(name = "status")
    private Status status = Status.inProcess;

    public Candidat(Client client, Job job, Status status) {
        this.client = client;
        this.job = job;
        this.status = status;
    }

    public void addCandidat(Client c, Job j) {
        this.client = c;
        this.job = j;
    }
}
