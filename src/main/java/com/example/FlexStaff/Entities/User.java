package com.example.FlexStaff.Entities;

import com.example.FlexStaff.Entities.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@MappedSuperclass
@Data
public class User implements UserDetails {

    @NonNull
    @Column(name = "firstName")
    public String firstName;

    @NonNull
    @Column(name = "lastName")
    public String lastName;

    @NonNull
    @Column(name = "email")
    public String email;

    @NonNull
    @Column(name = "password")
    public String password;

    @Column(name = "region")
    public String region;




    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password, String region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.region = region;

    }
    public User(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
