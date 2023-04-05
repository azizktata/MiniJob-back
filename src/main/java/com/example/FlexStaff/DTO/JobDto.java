package com.example.FlexStaff.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private String title ;

    private String description ;

    private String sector ;

    private String workTime ;

    private long salary ;

    private String location ;
}
