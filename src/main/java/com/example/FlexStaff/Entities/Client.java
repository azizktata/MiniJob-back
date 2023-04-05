package com.example.FlexStaff.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idC")
    private int idC ;
    @NonNull
    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    private String gender;

    @Lob
    @Column(name = "profileImgData")
    private byte[] profileImgData;

    @Column(name = "profileImgName")
    private String profileImgName;

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER)
    @JsonManagedReference (value = "client-apply")
    private List<Candidat> appliedJobs;

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER)
    @JsonManagedReference (value = "client-rate")
    @JsonIgnore
    private List<PartnerRating> ratings;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "favoritePartner",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "partner_id"))
    private List<Partner> favorites;



    public void favorPartner(Partner p) {
        favorites.add(p);
    }

    public void removeFavorie(Partner p) {
        favorites.remove(p);
    }
}
