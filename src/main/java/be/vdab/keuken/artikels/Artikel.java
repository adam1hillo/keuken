package be.vdab.keuken.artikels;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "artikels")
class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal aankoopprijs;
    private BigDecimal verkoopprijs;

    protected Artikel() {
    }

    Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
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
}
