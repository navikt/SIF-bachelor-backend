package com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv;

import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumenter_return;
import lombok.Data;

@Data
public class ResponeReturnFromDokArkiv_DTO {

    public String journalpostId;

    public boolean journalpostferdigstilt;

    private java.util.List<Dokumenter_return> dokumenter;

}
