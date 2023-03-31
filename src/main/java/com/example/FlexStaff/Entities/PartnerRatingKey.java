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
public class PartnerRatingKey implements Serializable {
    @Column(name = "client_id")
     private int clientId;

    @Column(name = "partner_id")
     private int partnerId;

    public PartnerRatingKey(int clientId, int partnerId) {
        this.clientId = clientId;
        this.partnerId = partnerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerRatingKey that = (PartnerRatingKey) o;
        return clientId == that.clientId && partnerId == that.partnerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, partnerId);
    }
}
