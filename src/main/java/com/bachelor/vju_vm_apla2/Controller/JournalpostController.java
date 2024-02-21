package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import no.nav.security.token.support.core.api.Protected;
import no.nav.security.token.support.core.api.Unprotected;
import reactor.core.publisher.Flux;



@Protected
@RestController
public class JournalpostController {

    private final SimpleService simpleService;

    @Autowired
    public JournalpostController(SimpleService simpleService){

        this.simpleService = simpleService;
    }

    //////////////////////////////////////////////////// HOVED METODER ///////////////////////////////////////////////////////////////////////

    //Søker på BrukerID og skal returnere en liste med journalposter

    //POST API, leverer liste med journalposter basert på query(uten filter) fra klienten. Henter liste fra Service klasse
    @CrossOrigin(origins = "http://localhost:3000") // Tillater CORS-forespørsler fra React-appen
    @PostMapping("/hentJournalpostListe")
    public ResponseEntity<String>hentJournalpostListe(@RequestBody String query,@RequestHeader HttpHeaders headers){

        System.out.println("Kontroller - Mottatt query: " + query +
                "\n" + "Kontroller - Mottatt headers: " + headers);

        String response = simpleService.hentJournalpostListe(query,headers);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .body(response);

    }



    //Metode for å hente et enkel PDF FIL
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/get-simple-pdf")
    public ResponseEntity<Resource> getPDF(@RequestParam("id") String journalId) {
        try {
            // Oppretter en ressurs som peker på PDF-filen i resources-mappen
            // ! Her kan du bytte ut example.pdf med den faktiske journalId'en for å finne frem.
            Resource pdfResource = new ClassPathResource("__files/"+journalId+".pdf");

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

    //////////////////////////////////////////////////////////////// PROTECTED API TEST ENDPOINTS///////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test/protected")
    public String protectedPath(){
        return "I am protected";
    }

    @Unprotected
    @GetMapping("/test/unprotected")
    public String unProtectedPath(){
        return "I am unprotected";
    }


    //////////////////////////////////////////////////////////////// ARKIVERTE METODER ////////////////////////////////////////////////////////////////


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
    public ResponseEntity<Flux<DataBuffer>> getPDF2(@RequestParam("id") String journalId) {
        System.out.println("Kontroller - getPDF: vi er inne i metoden");
        Flux<DataBuffer> pdfContent = simpleService.fetchPdfContent(journalId);
        System.out.println("Kontroller - getPDF: mottatt pdf fra service");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "document.pdf");

        System.out.println("Kontroller - getPDF: Sender pdf til klienten");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(pdfContent);

    }
}




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



