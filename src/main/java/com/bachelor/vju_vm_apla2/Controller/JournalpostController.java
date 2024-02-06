package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JournalpostController {

    private final SimpleService simpleService;

    @Autowired
    public JournalpostController(SimpleService simpleService){
        this.simpleService = simpleService;
    }

    //Dette er en test API for å sjekke om man får tilgang til GET kallet gjennom localhost:8080/getjp - Dette skal funke
    @CrossOrigin(origins = "http://localhost:3000") // Tillater CORS-forespørsler fra React-appen
    @GetMapping("/getjp")
    public String test(){
        return "Vi kan hente fra kontroller";
    }


    //Klienten gjør POST-Kall hos denne kontrolleren først.
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/journalpost")

    //Metoden tar i mot data som ligger i body fra klient og legger det inn i @RequestBody String journalpostdata
    //RequestHeader parser header info som blir mottatt fra klient.  'Content-Type': 'application/json', 'Accept': 'application/json',
    // **Denne metoden skal håndtere TOKEN**

    public ResponseEntity<String> handleJournalPostRequest(@RequestBody String journalPostData, @RequestHeader HttpHeaders headers) {

        System.out.println("Kontroller - Mottatt journalpost data: " + journalPostData +
                "\n" + "Kontroller - Mottatt headers: " + headers);

        String response = simpleService.handleJournalPostData(journalPostData, headers); //Sender data til Service layer (SimpleService) for å manipulering
        return ResponseEntity.ok(response); //returnerer data fra Service layer
    }
}
