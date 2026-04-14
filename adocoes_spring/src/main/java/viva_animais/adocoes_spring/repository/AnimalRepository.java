package viva_animais.adocoes_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import viva_animais.adocoes_spring.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
	// Busca o animal e já verifica se ele existe
    Animal findByIdAndDisponivel(Long id, int disponivel);

    // Método para atualizar direto via Query, se preferir performance
    @Modifying
    @Query("UPDATE Animal a SET a.disponivel = 0 WHERE a.id = :id")
    void marcarComoAdotado(Long id);
}