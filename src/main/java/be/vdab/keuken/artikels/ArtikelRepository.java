package be.vdab.keuken.artikels;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    @EntityGraph(attributePaths = "artikelGroep")
    List<Artikel> findByNaamContainingOrderByNaam(String woord);

    @Query("""
    select a from Artikel a where a.verkoopprijs - a.aankoopprijs >= :minimum order by a.verkoopprijs""")
    List<Artikel> findByMinimumWinst(BigDecimal minimum);

    @Query("""
    select min(a.verkoopprijs) from Artikel a""")
    BigDecimal findGoedkoopsteVerkoopprijs();
}
