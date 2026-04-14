package viva_animais.adocoes_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viva_animais.adocoes_spring.model.Adotante;

@Repository
public interface AdotanteRepository extends JpaRepository<Adotante, Long> {
    // Aqui o Spring já cria o SELECT * FROM nome da sua tabela
}