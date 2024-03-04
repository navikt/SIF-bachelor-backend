package com.bachelor.vju_vm_apla2.Models.DTO;

import com.bachelor.vju_vm_apla2.Models.POJO.graphql.Dokumentoversikt;
import com.bachelor.vju_vm_apla2.Models.POJO.graphql.Journalpost;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // This tells Lombok to generate a no-argument constructor
public class FraGrapQl_DTO {

    private Dokumentoversikt dokumentoversikt;
    private String errorMessage;
    private String detailedMessage;

    // New constructor with two String arguments
    public FraGrapQl_DTO(String errorMessage, String detailedMessage) {
        this.errorMessage = errorMessage;
        this.detailedMessage = detailedMessage;
    }

    // Lombok will automatically generate getters and setters, so you don't need to manually define them
}