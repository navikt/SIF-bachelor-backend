package com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv;

import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumentvariant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dokumenter {

    private String tittel;
    private String brevkode;
    private java.util.List<Dokumentvariant> dokumentvarianter;

}
