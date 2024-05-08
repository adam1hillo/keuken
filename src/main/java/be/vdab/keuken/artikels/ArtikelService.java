package be.vdab.keuken.artikels;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
class ArtikelService {
    private final ArtikelRepository artikelRepository;

    ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    Optional<Artikel> findById(long id) {
        return artikelRepository.findById(id);
    }
    @Transactional
    long create(NieuweArtikel nieuweArtikel) {
        Artikel artikel = new Artikel(nieuweArtikel.naam(), nieuweArtikel.aankoopprijs(), nieuweArtikel.verkoopprijs());
        artikelRepository.save(artikel);
        return artikel.getId();
    }
    List<Artikel> findByNaamBevat(String woord) {
        return artikelRepository.findByNaamContainingOrderByNaam(woord);
    }
}
