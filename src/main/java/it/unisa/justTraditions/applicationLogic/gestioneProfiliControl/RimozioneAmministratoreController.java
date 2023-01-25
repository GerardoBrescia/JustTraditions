package it.unisa.justTraditions.applicationLogic.gestioneProfiliControl;

import it.unisa.justTraditions.applicationLogic.autenticazioneControl.util.SessionAmministratore;
import it.unisa.justTraditions.storage.gestioneProfiliStorage.dao.AmministratoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rimozioneAmministratore")
public class RimozioneAmministratoreController {

  private static final String visualizzazioneAmministratoriController =
      "/visualizzazioneAmministratori";

  @Autowired
  private AmministratoreDao amministratoreDao;

  @Autowired
  private SessionAmministratore sessionAmministratore;

  @GetMapping
  public String get(@RequestParam Long id) {
    if (sessionAmministratore.getAmministratore().get().getId().equals(id)) {
      throw new IllegalArgumentException("Un amministratore non può eliminare se stesso");
    }

    if (amministratoreDao.existsById(id)) {
      amministratoreDao.deleteById(id);
    } else {
      throw new IllegalArgumentException("Non esiste un amministratore con questo id");
    }

    return "redirect:" + visualizzazioneAmministratoriController;
  }
}