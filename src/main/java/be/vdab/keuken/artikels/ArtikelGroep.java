package be.vdab.keuken.artikels;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artikelgroepen")
class ArtikelGroep {
    @Id
    private long id;
    private String naam;

    @OneToMany(mappedBy = "artikelGroep")
    @OrderBy("naam")
    private Set<Artikel> artikels;

    protected ArtikelGroep() {
    }

    public ArtikelGroep(String naam) {
        this.naam = naam;
        artikels = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Artikel> getArtikels() {
        return Collections.unmodifiableSet(artikels);
    }

    void voegArtikelToe(Artikel artikel) {
        if (!artikels.add(artikel)) {
            throw new ArtikelBestaatAlInArtikelGroepException();
        }
    }
}
