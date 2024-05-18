package com.bachelor.vju_vm_apla2.Models.DTO.Response;


import com.bachelor.vju_vm_apla2.Models.POJO.Saf.Journalpost;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ControllerReponse_DTO {
    List<Journalpost> newJournalposts;

}
