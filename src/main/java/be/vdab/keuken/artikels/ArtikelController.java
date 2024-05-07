package be.vdab.keuken.artikels;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
}
