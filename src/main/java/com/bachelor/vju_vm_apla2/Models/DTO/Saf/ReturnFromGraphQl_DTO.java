package com.bachelor.vju_vm_apla2.Models.DTO.Saf;

import com.bachelor.vju_vm_apla2.Models.POJO.Saf.Dokumentoversikt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // This tells Lombok to generate a no-argument constructor
@AllArgsConstructor
public class ReturnFromGraphQl_DTO {

    private Dokumentoversikt dokumentoversikt;
    private String errorMessage;

    // New constructor with two String arguments
    public ReturnFromGraphQl_DTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Lombok will automatically generate getters and setters, so you don't need to manually define them
}