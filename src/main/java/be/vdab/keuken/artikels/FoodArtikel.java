package be.vdab.keuken.artikels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


import java.math.BigDecimal;

@Entity
@DiscriminatorValue("F")
class FoodArtikel extends Artikel {

    private int houdbaarheid;

    protected FoodArtikel() {

    }

    FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, ArtikelGroep artikelGroep, int houdbaarheid) {
        super(naam, aankoopprijs, verkoopprijs, artikelGroep);
        this.houdbaarheid = houdbaarheid;
    }

    public int getHoudbaarheid() {
        return houdbaarheid;
    }
}
