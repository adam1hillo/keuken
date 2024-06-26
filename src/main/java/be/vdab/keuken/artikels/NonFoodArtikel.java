package be.vdab.keuken.artikels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("NF")
class NonFoodArtikel extends Artikel{

    private int garantie;

    protected NonFoodArtikel() {
    }

    NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, ArtikelGroep artikelGroep, int garantie) {
        super(naam, aankoopprijs, verkoopprijs, artikelGroep);
        this.garantie = garantie;
    }

    public int getGarantie() {
        return garantie;
    }
}
