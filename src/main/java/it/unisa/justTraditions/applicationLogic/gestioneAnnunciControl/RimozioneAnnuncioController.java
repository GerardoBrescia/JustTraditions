package it.unisa.justTraditions.applicationLogic.gestioneAnnunciControl;

import it.unisa.justTraditions.applicationLogic.autenticazioneControl.util.SessionCliente;
import it.unisa.justTraditions.storage.gestioneAnnunciStorage.dao.AnnuncioDao;
import it.unisa.justTraditions.storage.gestioneAnnunciStorage.entity.Annuncio;
import it.unisa.justTraditions.storage.gestioneProfiliStorage.dao.ArtigianoDao;
import it.unisa.justTraditions.storage.gestioneProfiliStorage.entity.Artigiano;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rimozioneAnnuncio")
public class RimozioneAnnuncioController {

  private static final String modificaAnnuncioSuccessView =
      "gestioneAnnunciView/modificaAnnuncioSuccess";

  @Autowired
  private AnnuncioDao annuncioDao;

  @Autowired
  private ArtigianoDao artigianoDao;

  @Autowired
  private SessionCliente sessionCliente;

  @GetMapping
  public String get(@RequestParam Long id) {
    Optional<Annuncio> optionalAnnuncio = annuncioDao.findById(id);
    if (optionalAnnuncio.isEmpty()) {
      throw new IllegalArgumentException();
    }

    Annuncio annuncio = optionalAnnuncio.get();

    Artigiano artigiano = (Artigiano) sessionCliente.getCliente().get();
    if (!annuncio.getArtigiano().equals(artigiano)) {
      throw new IllegalArgumentException();
    }

    artigiano.removeAnnuncio(annuncio);
    artigianoDao.save(artigiano);

    return modificaAnnuncioSuccessView;
  }
}