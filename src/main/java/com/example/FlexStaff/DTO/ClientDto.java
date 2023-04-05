package com.example.FlexStaff.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    private LocalDate birth;
    private String city;
}
