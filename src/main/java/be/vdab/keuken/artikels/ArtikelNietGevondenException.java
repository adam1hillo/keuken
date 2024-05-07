package be.vdab.keuken.artikels;

class ArtikelNietGevondenException extends RuntimeException {
    ArtikelNietGevondenException() {
        super("Artikel niet gevonden.");
    }
}
