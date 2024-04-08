package com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dokumentvariant {
    private String filtype;
    private byte[] fysiskDokument;
    private String variantformat;

    public Dokumentvariant(){
    }

}
