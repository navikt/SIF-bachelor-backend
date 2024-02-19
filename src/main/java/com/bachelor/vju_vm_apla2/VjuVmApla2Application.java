package com.bachelor.vju_vm_apla2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//Dette er runtime! Bruk VjuLocalApplication for å kjøre appen i development enviroment.
@SpringBootApplication
public class VjuVmApla2Application {

    public static void main(String[] args) {

        SpringApplication.run(VjuVmApla2Application.class, args);
    }

}
