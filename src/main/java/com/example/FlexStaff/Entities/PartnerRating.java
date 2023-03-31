package com.example.FlexStaff.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PartnerRating {
    @EmbeddedId
    private PartnerRatingKey id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @MapsId("partnerId")
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @Column(name = "stars")
    private int stars;

    public PartnerRating(Client client, Partner partner, int stars) {
        this.client = client;
        this.partner = partner;
        this.stars = stars;
    }
}
