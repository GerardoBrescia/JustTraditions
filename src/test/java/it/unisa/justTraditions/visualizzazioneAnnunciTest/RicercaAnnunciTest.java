package it.unisa.justTraditions.visualizzazioneAnnunciTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import it.unisa.justTraditions.storage.gestioneAnnunciStorage.dao.AnnuncioDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@SpringBootTest
@AutoConfigureMockMvc
public class RicercaAnnunciTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AnnuncioDao annuncioDao;

  @Test
  public void nomeTroppoLungo() {
    assertThatThrownBy(
        () -> test("Casa di Antonio Ciampa Napoli cantina nella vigna", "", null)
    ).hasCause(
        new IllegalArgumentException("La ricerca degli annunci non va a buon fine poiché"
            + " il nome inserito dall’utente è troppo lungo.")
    );
  }

  @Test
  public void provinciaNonValida() {
    assertThatThrownBy(
        () -> test("Cantine Santoro", "Bassoni", null)
    ).hasCause(new IllegalArgumentException("Provincia non esistente"));
  }

  @Test
  public void ricercaEffettuata()
      throws Exception {
    test("Cantine Santoro", "Avellino", status().isOk());
  }

  private void test(String nomeAttivita, String provincia, ResultMatcher resultMatcher)
      throws Exception {
    when(annuncioDao.findAll(any(), (Pageable) any())).thenReturn(Page.empty());

    mockMvc.perform(get("/ricercaAnnunci")
        .param("nomeAttivita", nomeAttivita)
        .param("provincia", provincia)
    ).andExpect(resultMatcher);
  }
}
