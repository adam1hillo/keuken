package be.vdab.keuken.artikels;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@Sql({"/artikelgroepen.sql", "/artikels.sql"})
@AutoConfigureMockMvc
class ArtikelControllerTest {

    private static final String ARTIKELS_TABLE = "artikels";
    private final JdbcClient jdbcClient;
    private final MockMvc mockMvc;

    ArtikelControllerTest(JdbcClient jdbcClient, MockMvc mockMvc) {
        this.jdbcClient = jdbcClient;
        this.mockMvc = mockMvc;
    }

    long idVanTestArtikel() {
        return jdbcClient.sql("select id from artikels where naam = 'test'").query(Long.class).single();
    }

    @Test
    void findByIdMetBestaandeIdVindtArtikel() throws Exception {
        mockMvc.perform(get("/artikels/{id}", idVanTestArtikel()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("naam").value("test"));
    }
    @Test
    void findByIdMetOnbestaandeIdMislukt() throws Exception {
        mockMvc.perform(get("/artikels/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}
