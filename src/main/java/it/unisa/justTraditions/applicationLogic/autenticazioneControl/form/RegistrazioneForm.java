package it.unisa.justTraditions.applicationLogic.autenticazioneControl.form;

import it.unisa.justTraditions.applicationLogic.autenticazioneControl.util.IsArtigianoConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@IsArtigianoConstraint(message = "No  artigiano")
public class RegistrazioneForm {
  @NotBlank(message = " nome vuoto")
  @Size(max = 30)
  private String nome;

  @NotBlank(message = "cognome vuoto ")
  @Size(max = 30)
  private String cognome;

  @NotBlank(message = "codiceFiscale vuoto ")
  @Pattern(regexp = "/^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$/i")
  @Size(max = 16)
  private String codiceFiscale;

  @NotBlank(message = "email vuota ")
  @Email(message = "formato email  errato")
  @Size(max = 319)
  private String email;

  @NotBlank(message = "passworld vuota ")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct})[A-Za-z\\d\\p{Punct}]{8,}$")
  private String passworld;

  @NotNull
  private Boolean Artigiano;

  @Size(max = 27)
  private String iban;

  public RegistrazioneForm() {

  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassworld() {
    return passworld;
  }

  public void setPassworld(String passworld) {
    this.passworld = passworld;
  }

  public Boolean isArtigiano() {
    return Artigiano;
  }

  public void setArtigiano(Boolean artigiano) {
    Artigiano = artigiano;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }
}