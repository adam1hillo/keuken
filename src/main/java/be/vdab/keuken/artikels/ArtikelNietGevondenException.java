package be.vdab.keuken.artikels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class ArtikelNietGevondenException extends RuntimeException {
    ArtikelNietGevondenException() {
        super("Artikel niet gevonden.");
    }
}
