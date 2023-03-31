package com.example.FlexStaff.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class CandidatKey implements Serializable {

    @Column(name = "client_id")
    int clientId;

    @Column(name = "job_id")
    int jobId;

    public CandidatKey(int clientId, int jobId) {
        this.clientId = clientId;
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatKey that = (CandidatKey) o;
        return clientId == that.clientId && jobId == that.jobId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, jobId);
    }
}
