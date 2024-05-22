package be.vdab.keuken.artikels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class ArtikelGroepNietGevondenException extends RuntimeException{
    ArtikelGroepNietGevondenException() {
        super("Artikelgroep niet gevonden.");
    }
}
