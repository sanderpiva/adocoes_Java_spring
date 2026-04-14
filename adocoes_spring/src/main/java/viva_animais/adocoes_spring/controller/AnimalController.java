package viva_animais.adocoes_spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import viva_animais.adocoes_spring.model.Animal;
import viva_animais.adocoes_spring.repository.AnimalRepository;

@RestController
@RequestMapping("/api/animais")
@Validated // NOVO: Permite validar @PathVariable
public class AnimalController {

    @Autowired
    private AnimalRepository repository;

    @GetMapping
    public List<Animal> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Animal salvar(@Valid @RequestBody Animal novoAnimal) {
        // NOVO: @Valid valida nome, espécie, raça, descrição
        return repository.save(novoAnimal);
    }

    @GetMapping("/{id}")
    public Animal buscarPorId(@PathVariable @Min(1) Long id) {
        // NOVO: @Min(1) valida que o ID é positivo
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Animal atualizar(@PathVariable @Min(1) Long id, @Valid @RequestBody Animal dadosNovos) {
        // NOVO: @Valid valida os dados novos
        // NOVO: @Min(1) valida o ID
        return repository.findById(id).map(animal -> {
            animal.setNome(dadosNovos.getNome());
            animal.setEspecie(dadosNovos.getEspecie());
            animal.setRaca(dadosNovos.getRaca());
            animal.setDescricao(dadosNovos.getDescricao());
            animal.setDisponivel(dadosNovos.getDisponivel());
            return repository.save(animal);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable @Min(1) Long id) {
        // NOVO: @Min(1) valida que o ID é positivo
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("Excluído");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Este animal possui histórico de adoção e não pode ser removido.");
        }
    }
}