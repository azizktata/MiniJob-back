package com.example.FlexStaff.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "client")
    private List<Candidat> appliedJobs;

    @OneToMany(mappedBy = "client")
    private List<PartnerRating> ratings;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "favoritePartner",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "partner_id"))
    private List<Partner> favorites;


    public Client(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password, String region, @NonNull LocalDate birth, String city, String gender) {
        super(firstName, lastName, email, password, region);
        this.birth = birth;
        this.city = city;
        this.gender = gender;
    }

    public void favorPartner(Partner p) {
        favorites.add(p);
    }

    public void removeFavorie(Partner p) {
        favorites.remove(p);
    }
}
