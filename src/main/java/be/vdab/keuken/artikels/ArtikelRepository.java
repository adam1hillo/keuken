package be.vdab.keuken.artikels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByNaamContainingOrderByNaam(String woord);

    @Query("""
    select a from Artikel a where a.verkoopprijs - a.aankoopprijs >= :minimum order by a.verkoopprijs""")
    List<Artikel> findByMinimumWinst(BigDecimal minimum);

    @Query("""
    select min(a.verkoopprijs) from Artikel a""")
    BigDecimal findGoedkoopsteVerkoopprijs();
}
