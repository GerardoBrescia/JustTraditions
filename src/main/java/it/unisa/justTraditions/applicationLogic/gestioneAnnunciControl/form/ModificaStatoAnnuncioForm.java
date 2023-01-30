package it.unisa.justTraditions.applicationLogic.gestioneAnnunciControl.form;

import it.unisa.justTraditions.storage.gestioneAnnunciStorage.entity.Annuncio;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@NuovoStatoIsValidConstraint
@MotivoDelRifiutoIsValidConstraint
public class ModificaStatoAnnuncioForm {

    @NotNull(message = "Id annuncio assente")
    private Long idAnnuncio;

    @NotNull(message = "Nuovo stato non presente")
    private Annuncio.Stato nuovoStato;

    @Size(max = 255, message = "Motivo del rifiuto troppo lungo")
    private String motivoDelRifiuto;

    public ModificaStatoAnnuncioForm() {
    }

    public ModificaStatoAnnuncioForm(Long idAnnuncio, Annuncio.Stato nuovoStato,
                                     String motivoDelRifiuto) {
        this.idAnnuncio = idAnnuncio;
        this.nuovoStato = nuovoStato;
        this.motivoDelRifiuto = motivoDelRifiuto;
    }

    public Long getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(Long idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }

    public Annuncio.Stato getNuovoStato() {
        return nuovoStato;
    }

    public void setNuovoStato(
            Annuncio.Stato nuovoStato) {
        this.nuovoStato = nuovoStato;
    }

    public String getMotivoDelRifiuto() {
        return motivoDelRifiuto;
    }

    public void setMotivoDelRifiuto(String motivoDelRifiuto) {
        this.motivoDelRifiuto = motivoDelRifiuto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModificaStatoAnnuncioForm{");
        sb.append("idAnnuncio=").append(idAnnuncio);
        sb.append(", nuovoStato=").append(nuovoStato);
        sb.append(", motivoDelRifiuto='").append(motivoDelRifiuto).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
