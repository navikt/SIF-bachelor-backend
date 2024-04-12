package com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Dokumentvariant {
    private String filtype;
    private byte[] fysiskDokument;
    private String variantformat;

    public Dokumentvariant(){
    }

}
