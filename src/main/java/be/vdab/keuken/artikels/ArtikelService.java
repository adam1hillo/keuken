package be.vdab.keuken.artikels;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
class ArtikelService {
    private final ArtikelRepository artikelRepository;
    private final ArtikelGroepRepository artikelGroepRepository;

    ArtikelService(ArtikelRepository artikelRepository, ArtikelGroepRepository artikelGroepRepository) {
        this.artikelRepository = artikelRepository;
        this.artikelGroepRepository = artikelGroepRepository;
    }

    Optional<Artikel> findById(long id) {
        return artikelRepository.findById(id);
    }

    List<Artikel> findAll() {
        return artikelRepository.findAll();
    }

    @Transactional
    long create(NieuwFoodArtikel nieuwArtikel) {
        ArtikelGroep artikelGroep = artikelGroepRepository.findById(nieuwArtikel.artikelgroepId())
                .orElseThrow(ArtikelGroepNietGevondenException::new);
        FoodArtikel artikel = new FoodArtikel(nieuwArtikel.naam(), nieuwArtikel.aankoopprijs(), nieuwArtikel.verkoopprijs(), artikelGroep, nieuwArtikel.houdbaarheid());
        artikelRepository.save(artikel);
        return artikel.getId();
    }
    @Transactional
    long create(NieuwNonFoodArtikel nieuwArtikel) {
        ArtikelGroep artikelGroep = artikelGroepRepository.findById(nieuwArtikel.artikelgroepId())
                .orElseThrow(ArtikelGroepNietGevondenException::new);
        NonFoodArtikel artikel = new NonFoodArtikel(nieuwArtikel.naam(), nieuwArtikel.aankoopprijs(), nieuwArtikel.verkoopprijs(), artikelGroep, nieuwArtikel.garantie());
        artikelRepository.save(artikel);
        return artikel.getId();
    }



    List<Artikel> findByNaamBevat(String woord) {
        return artikelRepository.findByNaamContainingOrderByNaam(woord);
    }
    List<Artikel> findByMinimumWinst(BigDecimal minimum) {
        return artikelRepository.findByMinimumWinst(minimum);
    }
    BigDecimal findGoedkopsteVerkoopprijs() {
        return artikelRepository.findGoedkoopsteVerkoopprijs();
    }
    @Transactional
    void wijzigVerkoopprijs(long id, BigDecimal verkoopprijs) {
        artikelRepository.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new)
                .setVerkoopprijs(verkoopprijs);
    }
}
