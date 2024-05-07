package be.vdab.keuken.artikels;

import org.springframework.data.jpa.repository.JpaRepository;

interface ArtikelRepository extends JpaRepository<Artikel, Long> {
}
