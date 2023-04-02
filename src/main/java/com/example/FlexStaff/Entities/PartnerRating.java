package com.example.FlexStaff.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference (value = "client-rate")
    private Client client;

    @ManyToOne
    @MapsId("partnerId")
    @JoinColumn(name = "partner_id")
    @JsonBackReference (value = "partner-rate")
    private Partner partner;

    @Column(name = "stars")
    private int stars;

    public PartnerRating(Client client, Partner partner, int stars) {
        this.client = client;
        this.partner = partner;
        this.stars = stars;
    }
    public PartnerRating(int stars) {
        this.stars = stars;
    }

    public void addRate(Client c, Partner p) {
        this.client = c;
        this.partner = p;
    }
}
