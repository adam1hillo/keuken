package be.vdab.keuken.artikels;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("artikels")
class ArtikelController {

    private final ArtikelService artikelService;

    ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    @GetMapping("{id}")
    Artikel findById(@PathVariable long id) {
        return artikelService.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new);
    }
    @PostMapping
    long create(@RequestBody @Valid NieuweArtikel nieuweArtikel) {
        return artikelService.create(nieuweArtikel);
    }
    @GetMapping(params = "naamBevat")
    List<Artikel> findByNaamBevat(String naamBevat) {
        return artikelService.findByNaamBevat(naamBevat);
    }
    @GetMapping(params = "minimumWinst")
    List<Artikel> findByMinimumWinst(BigDecimal minimumWinst) {
        return artikelService.findByMinimumWinst(minimumWinst);
    }
    @GetMapping("verkoopprijzen/goedkoopste")
    BigDecimal findGoedkoopsteVerkoopprijs() {
        return artikelService.findGoedkopsteVerkoopprijs();
    }
    @PatchMapping("{id}/verkoopprijs")
    void wijzigVerkoopprijs(@PathVariable long id, @RequestBody @NotNull @PositiveOrZero BigDecimal prijs) {
        artikelService.wijzigVerkoopprijs(id, prijs);
    }
}
