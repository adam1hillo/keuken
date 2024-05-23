package be.vdab.keuken.artikels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
class ArtikelBestaatAlInArtikelGroepException extends RuntimeException{
    ArtikelBestaatAlInArtikelGroepException() {
        super("Artikel bestaat al in dit artikelgroep.");
    }
}
