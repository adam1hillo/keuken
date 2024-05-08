package be.vdab.keuken.artikels;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByNaamContainingOrderByNaam(String woord);
}
