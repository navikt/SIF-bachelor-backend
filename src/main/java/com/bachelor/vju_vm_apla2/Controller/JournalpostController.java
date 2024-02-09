package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

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

    //////////////////////////////////////////////////// HOVED METODER ///////////////////////////////////////////////////////////////////////

    //Søker på BrukerID og skal returnere en liste med journalposter
    @CrossOrigin(origins = "http://localhost:3000") // Tillater CORS-forespørsler fra React-appen
    @GetMapping("/simple_hentJournalPoster")
    public String simple_hentJournalPosterk(){
        System.out.println("Den blir ikke truffet");
        return "Vi kan hente fra journalposter";
    }


    @CrossOrigin(origins = "http://localhost:3000") // Tillater CORS-forespørsler fra React-appen
    @GetMapping("/hentJournalPoster/{brukerID}")
    public String hentJournalPoster(@PathVariable String brukerID) {
        // Bruk brukerID her, for eksempel for å hente informasjon fra en database
        return "Vi kan hente fra journalposter for brukerID: " + brukerID;
    }



    //UTEN PDF
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


    //Metode for å hente et enkel PDF FIL
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/get-simple-pdf")
    public ResponseEntity<Resource> getPDF() {
        try {
            // Oppretter en ressurs som peker på PDF-filen i resources-mappen
            Resource pdfResource = new ClassPathResource("__files/648126654.pdf");

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


    //////////////////////////////////////////////////////////////// TESTER ////////////////////////////////////////////////////////////////

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
    /*
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getpdf")
    public ResponseEntity<Flux<DataBuffer>> getPDF() {
        System.out.println("Kontroller - getPDF: vi er inne i metoden");
        Flux<DataBuffer> pdfContent = simpleService.fetchPdfContent();
        System.out.println("Kontroller - getPDF: mottatt pdf fra service");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "document.pdf");

        System.out.println("Kontroller - getPDF: Sender pdf til klienten");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);

    }

     */



    /*/PDF TEST MED STØRRELSE

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getpdf")
    public ResponseEntity<Flux<DataBuffer>> getPDF() {
        System.out.println("Kontroller - getPDF: vi er inne i metoden");
        final AtomicInteger totalSize = new AtomicInteger(0); // Oppretter en AtomicInteger for å holde på den totale størrelsen

        Flux<DataBuffer> pdfContent = simpleService.fetchPdfContent()
                .doOnNext(dataBuffer -> {
                    int size = dataBuffer.readableByteCount();
                    System.out.println("Kontroller - getPDF: Mottatt buffer med størrelse: " + size + " bytes");
                    totalSize.addAndGet(size); // Akkumulerer størrelsen på hver buffer
                })
                .doOnComplete(() -> {
                    System.out.println("Kontroller - getPDF: Fullført sending av PDF til klienten. Total størrelse: " + totalSize.get() + " bytes");
                });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "document.pdf");

        System.out.println("Kontroller - getPDF: Sender pdf til klienten");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }

     */



}
