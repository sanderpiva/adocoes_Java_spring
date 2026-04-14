package viva_animais.adocoes_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import viva_animais.adocoes_spring.model.Adocao;
import viva_animais.adocoes_spring.model.AdocaoDetalhadaDTO;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    
    @Query("SELECT new viva_animais.adocoes_spring.model.AdocaoDetalhadaDTO(" +
           "a.adocoes_id, ad.id, ad.nome, ad.telefone, ad.email, an.nome, a.data, a.status) " + 
           "FROM Adocao a JOIN a.adotante ad JOIN a.animal an " +
           "WHERE a.status = 'ATIVA'")
    List<AdocaoDetalhadaDTO> buscarRelatorioCompleto();
    
    @Query("SELECT new viva_animais.adocoes_spring.model.AdocaoDetalhadaDTO(" +
           "a.adocoes_id, ad.id, ad.nome, ad.telefone, ad.email, an.nome, a.data, a.status) " + 
           "FROM Adocao a JOIN a.adotante ad JOIN a.animal an " +
           "WHERE a.status = 'DEVOLVIDA'") 
    List<AdocaoDetalhadaDTO> buscarRelatorioDevolvidos();
}