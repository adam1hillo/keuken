package be.vdab.keuken.artikels;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name = "soort")
abstract class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal aankoopprijs;
    private BigDecimal verkoopprijs;

    @ElementCollection
    @CollectionTable(name = "kortingen",
    joinColumns = @JoinColumn(name = "artikelId"))
    @OrderBy("vanafAantal")
    private Set<Korting> kortingen;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artikelgroepId")
    private ArtikelGroep artikelGroep;

    protected Artikel() {};

    Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, ArtikelGroep artikelGroep) {
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
        this.artikelGroep = artikelGroep;
        kortingen = new LinkedHashSet<>();
    }
    void setVerkoopprijs(BigDecimal verkoopprijs) {
        this.verkoopprijs = verkoopprijs;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getAankoopprijs() {
        return aankoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }

    public Set<Korting> getKortingen() {
        return Collections.unmodifiableSet(kortingen);
    }

    public ArtikelGroep getArtikelGroep() {
        return artikelGroep;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Artikel artikel)) return false;
        return naam.equalsIgnoreCase(artikel.naam);
    }

    @Override
    public int hashCode() {
        return naam.toLowerCase().hashCode();
    }
}
