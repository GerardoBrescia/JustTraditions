package it.unisa.justTraditions.storage.gestioneProfiliStorage.dao;

import it.unisa.justTraditions.storage.gestioneProfiliStorage.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UtenteDao<T extends Utente>
    extends JpaRepository<T, Long> {
}
