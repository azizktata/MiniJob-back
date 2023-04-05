package com.example.FlexStaff.DTO;

import com.example.FlexStaff.Entities.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatDto {
    private Status status;
}
