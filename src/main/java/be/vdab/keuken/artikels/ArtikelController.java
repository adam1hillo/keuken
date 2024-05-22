package be.vdab.keuken.artikels;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("artikels")
class ArtikelController {

    private final ArtikelService artikelService;

    ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    @GetMapping
    Stream<ArtikelBeknopt> findAll() {
        return artikelService.findAll()
                .stream()
                .map(ArtikelBeknopt::new);
    }

    @GetMapping("{id}")
    ArtikelBeknopt findById(@PathVariable long id) {
        return artikelService.findById(id)
                .map(ArtikelBeknopt::new)
                .orElseThrow(ArtikelNietGevondenException::new);
    }
    @GetMapping("{id}/artikelGroepNaam")
    String findArtikelGroepNaamByArtikelId(@PathVariable long id) {
        return artikelService.findById(id)
                .map(artikel -> artikel.getArtikelGroep().getNaam())
                .orElseThrow(ArtikelNietGevondenException::new);
    }
    @PostMapping("food")
    long create(@RequestBody @Valid NieuwFoodArtikel nieuwArtikel) {
        return artikelService.create(nieuwArtikel);
    }
    @PostMapping("nonfood")
    long create(@RequestBody @Valid NieuwNonFoodArtikel nieuwArtikel) {
        return artikelService.create(nieuwArtikel);
    }



    @GetMapping(params = "naamBevat")
    Stream<ArtikelBeknopt> findByNaamBevat(String naamBevat) {
        return artikelService.findByNaamBevat(naamBevat)
                .stream()
                .map(ArtikelBeknopt::new);
    }
    @GetMapping(params = "minimumWinst")
    Stream<ArtikelBeknopt> findByMinimumWinst(BigDecimal minimumWinst) {
        return artikelService.findByMinimumWinst(minimumWinst)
                .stream()
                .map(ArtikelBeknopt::new);
    }
    @GetMapping("verkoopprijzen/goedkoopste")
    BigDecimal findGoedkoopsteVerkoopprijs() {
        return artikelService.findGoedkopsteVerkoopprijs();
    }
    @PatchMapping("{id}/verkoopprijs")
    void wijzigVerkoopprijs(@PathVariable long id, @RequestBody @NotNull @PositiveOrZero BigDecimal prijs) {
        artikelService.wijzigVerkoopprijs(id, prijs);
    }
    private record ArtikelBeknopt(long id, String naam, BigDecimal verkoopprijs) {
        ArtikelBeknopt(Artikel artikel) {
            this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs());
        }
    }
}
