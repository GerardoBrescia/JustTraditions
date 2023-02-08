package it.unisa.justTraditions.gestioneAnnunciTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import it.unisa.justTraditions.applicationLogic.autenticazioneControl.util.SessionCliente;
import it.unisa.justTraditions.storage.gestioneProfiliStorage.dao.ArtigianoDao;
import it.unisa.justTraditions.storage.gestioneProfiliStorage.entity.Artigiano;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * Implementa il test di unità per SottomissioneAnnuncioController.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SottomissioneAnnuncioTest {

  private static final String sottomissioneAnnuncioView =
      "gestioneAnnunciView/sottomissioneAnnuncio";
  private static final String modificaAnnuncioSuccessView =
      "gestioneAnnunciView/modificaAnnuncioSuccess";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SessionCliente sessionCliente;

  @MockBean
  private ArtigianoDao artigianoDao;

  @Test
  public void lunghezzaNomeAttivitaNonValida()
      throws Exception {
    test(
        "la Casa del vicino Tobia in mezzo alle colline di Salerno",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void lunghezzaDescrizioneNonValida()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa     del      vicino        Tobia       in       mezzo    alle  "
            + "   colline         di      Salerno          e            un'attività        che   "
            + "       si      occupa          da trent'anni della produzione      di           "
            + "vini, da 5 anni                è diventata         anche             una          "
            + "      attività             di degustazione           di              vini          "
            + "       nostrani .\n Noi                  pensiamo           di essere una          "
            + "  delle poche                attività              che               può            "
            + " offrire             un                vasto             catalogo           di     "
            + "          vidi                di                ogni genere           e     "
            + "    annata . Avendo             anche             un           \n"
            + " Nel nostro orto             possiamo            offrire            anche      "
            + "    degli            ottimi            antipasti          a         base         "
            + " di        verdure            a             chilometro 0,ma non solo in rare"
            + " occasioni organizziamo pranzi e cene a base di carne o pesce .\n",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void lunghezzaIndirizzoNonValida()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine della casa sull'albero di fronte al giardino romano "
            + "dell'imperatore Ottaviano di buona avventura Fiocchi Davena Del Fiume 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void provinciaNonValida()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Visciano",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void lunghezzaServiziOffertiNonValida()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "  Degustazione di vini artigianali con aperitivo             "
            + "offerta dalla nostra attività che si occupa da              "
            + "trent'anni della produzione di vini, da 5 anni è diventata                "
            + "anche una              attività di degustazione di vini nostrani.",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void formatoPrezzoVisitaNonValido()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30;50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void giornoNonValido()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MOMDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void formatoOrarioInizioNonValido()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "1000",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void formatoOrarioFineNonValido()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "1300",
        9,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void numMaxPersonePerVisitaNonValido()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        -20,
        new byte[1024 * 1024 * 14],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void fotoTroppoGrande()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 50],
        view().name(sottomissioneAnnuncioView)
    );
  }

  @Test
  public void operazioneEffettuata()
      throws Exception {
    test(
        "la Casa del vicino Tobia",
        "la Casa del vicino Tobia in mezzo alle colline di Salerno e un'attività "
            + "che si occupa da trent'anni della produzione di vini, da 5 anni è diventata anche "
            + "una attività di degustazione di vini nostrani .",
        "Via Carmine Fiocchi 28",
        "Salerno",
        "Degustazione di vini artigianali con aperitivo",
        "30.50",
        "MONDAY",
        "10:00",
        "13:00",
        9,
        new byte[1024 * 1024 * 14],
        view().name(modificaAnnuncioSuccessView)
    );
  }

  private void test(String nomeAttivita, String descrizione, String indirizzoAttivita,
                    String provinciaAttivita, String serviziOfferti, String prezzoVisita,
                    String giorno, String orarioInizio, String orarioFine,
                    Integer numMaxPersonePerVisita, byte[] foto, ResultMatcher resultMatcher)
      throws Exception {
    when(sessionCliente.getCliente()).thenReturn(Optional.of(new Artigiano()));

    mockMvc.perform(multipart("/sottomissioneAnnuncio")
        .file("foto", foto)
        .param("nomeAttivita", nomeAttivita)
        .param("descrizione", descrizione)
        .param("indirizzoAttivita", indirizzoAttivita)
        .param("provinciaAttivita", provinciaAttivita)
        .param("serviziOfferti", serviziOfferti)
        .param("prezzoVisita", prezzoVisita)
        .param("visite[0].giorno", giorno)
        .param("visite[0].orarioInizio", orarioInizio)
        .param("visite[0].orarioFine", orarioFine)
        .param("numMaxPersonePerVisita", String.valueOf(numMaxPersonePerVisita))
    ).andExpect(resultMatcher);
  }
}
