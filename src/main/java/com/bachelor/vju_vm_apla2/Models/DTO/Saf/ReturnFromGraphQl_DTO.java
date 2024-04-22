package com.bachelor.vju_vm_apla2.Models.DTO.Saf;

import com.bachelor.vju_vm_apla2.Models.POJO.Saf.Dokumentoversikt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnFromGraphQl_DTO {

    private Dokumentoversikt dokumentoversikt;
    private String errorMessage;



    public ReturnFromGraphQl_DTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}


