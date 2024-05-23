package be.vdab.keuken.artikels;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("artikelGroepen")
class ArtikelGroepController {
    private final ArtikelGroepService artikelGroepService;

    ArtikelGroepController(ArtikelGroepService artikelGroepService) {
        this.artikelGroepService = artikelGroepService;
    }

    @GetMapping("{id}/artikels")
    Stream<String> findArtikelsVan(@PathVariable long id) {
        return artikelGroepService.findById(id)
//                .map(artikelGroep -> artikelGroep.getArtikels()
//                        .stream()
//                        .map(Artikel::getNaam))
//                .orElseThrow(ArtikelGroepNietGevondenException::new);
                .orElseThrow(ArtikelGroepNietGevondenException::new)
                .getArtikels()
                .stream()
                .map(Artikel::getNaam);

    }
}
