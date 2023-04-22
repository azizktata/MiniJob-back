package com.example.FlexStaff.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String businessName;
    private String description;
    private String businessLocation;
    private String sector;
}
