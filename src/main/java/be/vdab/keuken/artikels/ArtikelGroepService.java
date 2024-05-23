package be.vdab.keuken.artikels;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
class ArtikelGroepService {

    private final ArtikelGroepRepository artikelGroepRepository;

    ArtikelGroepService(ArtikelGroepRepository artikelGroepRepository) {
        this.artikelGroepRepository = artikelGroepRepository;
    }

    Optional<ArtikelGroep> findById(long id) {
        return artikelGroepRepository.findById(id);
    }
}
