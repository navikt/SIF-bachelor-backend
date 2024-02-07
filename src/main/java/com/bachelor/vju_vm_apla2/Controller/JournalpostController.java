package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

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


    // UTEN PDF
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


    /*
    //MED PDF
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/journalpost")
    public ResponseEntity<Flux<DataBuffer>> handleJournalPostRequest(@RequestBody String journalPostData, @RequestHeader HttpHeaders headers) {
        System.out.println("Kontroller - Mottatt journalpost data: " + journalPostData + "\n" + "Kontroller - Mottatt headers: " + headers);

        // Riktig kall på service-metoden på simpleService-instansen
        Flux<DataBuffer> pdfContent = simpleService.handleAndFetchDocument(journalPostData, headers);

        // Returnerer PDF-innholdet direkte som en strøm av DataBuffer
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Setter riktig Content-Type
                .body(pdfContent); // Sender strømmen av DataBuffer tilbake til klienten
    }

     */

    //PDF TEST
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getpdf")
    public ResponseEntity<Resource> getPDF() {
        try {
            // Oppretter en ressurs som peker på PDF-filen i resources-mappen
            Resource pdfResource = new ClassPathResource("dokumenter/648126654.pdf");

            if (pdfResource.exists() || pdfResource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + pdfResource.getFilename() + "\"")
                        .body(pdfResource);
            } else {
                throw new RuntimeException("Kunne ikke lese filen!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Feil under behandling av filen", e);
        }
    }

}
