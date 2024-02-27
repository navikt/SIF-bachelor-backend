package com.bachelor.vju_vm_apla2.Models.DTO;

import com.bachelor.vju_vm_apla2.graphql.Journalpost;
import lombok.Data;

import java.util.List;
@Data
public class FraGrapQl_DTO {

    private List<Journalpost> journalposter;
}
