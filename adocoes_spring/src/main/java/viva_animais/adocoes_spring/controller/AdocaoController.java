package viva_animais.adocoes_spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional; 
import org.springframework.validation.annotation.Validated;

import viva_animais.adocoes_spring.model.Adocao;
import viva_animais.adocoes_spring.model.AdocaoDetalhadaDTO;
import viva_animais.adocoes_spring.model.Animal;
import viva_animais.adocoes_spring.repository.AdocaoRepository;
import viva_animais.adocoes_spring.repository.AnimalRepository;

@RestController
@RequestMapping("/api/adocoes")
@Validated // NOVO: Permite validar @PathVariable
public class AdocaoController {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private AnimalRepository animalRepository; 

    @GetMapping("/detalhes")
    public List<AdocaoDetalhadaDTO> listarTudo() {
        return repository.buscarRelatorioCompleto(); 
    }
    
    @GetMapping("/detalhesDevolvidos")
    public List<AdocaoDetalhadaDTO> listarDevolvidos() {
        return repository.buscarRelatorioDevolvidos(); 
    }
     
    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<String> salvarAdocao(@RequestBody Adocao adocao) {
        Long animalId = adocao.getAnimal().getId();
        Long adotanteId = adocao.getAdotante().getId();

        List<AdocaoDetalhadaDTO> devolvidos = repository.buscarRelatorioDevolvidos();
        boolean bloqueado = devolvidos.stream()
                .anyMatch(d -> d.getAdotanteId().equals(adotanteId));

        if (bloqueado) {
            return ResponseEntity.badRequest()
                    .body("Este adotante está bloqueado por ter devolvido um animal anteriormente.");
        }

        Animal animal = animalRepository.findByIdAndDisponivel(animalId, 1);
        
        if (animal == null) {
            return ResponseEntity.badRequest().body("O animal não está disponível.");
        }

        adocao.setStatus("ATIVA"); 
        repository.save(adocao);

        animal.setDisponivel(0);
        animalRepository.save(animal);

        return ResponseEntity.ok("Adoção realizada com sucesso!");
    }
    
    // EXCLUIR: Deleta do banco
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> desfazerAdocao(@PathVariable Long id) {
        try {
     
        	Adocao adocao = repository.findById(id).orElse(null);

            if (adocao == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Erro: Registro de adoção ID " + id + " não encontrado.");
            }

            Animal animal = adocao.getAnimal(); 

            // Para ter um historico de adoção do animal: EM VEZ DE DELETAR:
            // repository.delete(adocao); 

            // ARQUIVE:
            adocao.setStatus("DEVOLVIDA");
            repository.save(adocao);

            if (animal != null) {
                animal.setDisponivel(1);
                animalRepository.save(animal);
            }

            return ResponseEntity.ok("Adoção desfeita! O animal " + 
                                     (animal != null ? animal.getNome() : "") + 
                                     " está disponível (status 1) novamente.");

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("Erro de integridade ao desfazer a adoção.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro inesperado: " + e.getMessage());
        }
    }    
}