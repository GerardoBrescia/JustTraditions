package it.unisa.justTraditions.applicationLogic.autenticazioneControl;

import it.unisa.justTraditions.applicationLogic.autenticazioneControl.form.LoginForm;
import it.unisa.justTraditions.applicationLogic.autenticazioneControl.util.PasswordEncryptor;
import it.unisa.justTraditions.applicationLogic.autenticazioneControl.util.SessionCliente;
import it.unisa.justTraditions.storage.gestioneProfiliStorage.dao.ClienteDao;
import it.unisa.justTraditions.storage.gestioneProfiliStorage.entity.Cliente;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

  private static final String loginView = "autenticazioneView/login";
  private static final String homeController = "/";

  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private SessionCliente sessionCliente;

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  @GetMapping
  public String get(@ModelAttribute LoginForm loginForm, Model model) {
    model.addAttribute("nameLogin", "/login");
    return loginView;
  }

  @PostMapping
  public String post(@ModelAttribute @Valid LoginForm loginForm,
                     BindingResult bindingResult, Model model) {
    model.addAttribute("nameLogin", "/login");
    if (bindingResult.hasErrors()) {
      return loginView;
    }

    Optional<Cliente> optionalCliente = clienteDao.findByEmail(loginForm.getEmail());

    if (optionalCliente.isEmpty()) {
      model.addAttribute("existsEmail", false);
      return loginView;
    }

    Cliente cliente = optionalCliente.get();
    if (passwordEncryptor.checkPassword(loginForm.getPassword(), cliente.getPassword())) {
      sessionCliente.setCliente(cliente);
    } else {
      model.addAttribute("passwordErrata", true);

      return loginView;
    }

    return "redirect:" + homeController;
  }
}
