package be.vdab.keuken.artikels;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/artikels.sql")
class ArtikelRepositoryTest {
    private static final String ARTIKELS_TABLE = "artikels";
    private final ArtikelRepository artikelRepository;
    private final JdbcClient jdbcClient;

    ArtikelRepositoryTest(ArtikelRepository artikelRepository, JdbcClient jdbcClient) {
        this.artikelRepository = artikelRepository;
        this.jdbcClient = jdbcClient;
    }

    long idVanTestArtikel() {
        return jdbcClient.sql("select id from artikels where naam = 'test'").query(Long.class).single();
    }

    @Test
    void findByIdMetBestaandeIdVindtArtikel() {
        assertThat(artikelRepository.findById(idVanTestArtikel()).get().getNaam()).isEqualTo("test");
    }
    @Test
    void findByIdMetOnbestaandeIdMislukt() {
        assertThat(artikelRepository.findById(Long.MAX_VALUE)).isEmpty();
    }
}
