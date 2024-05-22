package be.vdab.keuken.artikels;

import jakarta.persistence.*;

@Entity
@Table(name = "artikelgroepen")
class ArtikelGroep {
    @Id
    private long id;
    private String naam;


    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
