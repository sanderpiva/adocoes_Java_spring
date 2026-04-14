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

import viva_animais.adocoes_spring.model.Adotante;
import viva_animais.adocoes_spring.repository.AdotanteRepository;

@RestController
@RequestMapping("/api/adotantes")
@Validated // NOVO: Permite validar @PathVariable
public class AdotanteController {

    @Autowired
    private AdotanteRepository repository;

    @GetMapping
    public List<Adotante> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Adotante salvar(@Valid @RequestBody Adotante novoAdotante) {
        // NOVO: @Valid valida nome, email, telefone
        return repository.save(novoAdotante);
    }
    
    @GetMapping("/{id}")
    public Adotante buscarPorId(@PathVariable @Min(1) Long id) {
        // NOVO: @Min(1) valida que o ID é positivo
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Adotante atualizar(@PathVariable @Min(1) Long id, @Valid @RequestBody Adotante dadosNovos) {
        // NOVO: @Valid valida os dados novos
        // NOVO: @Min(1) valida o ID
        return repository.findById(id).map(adotante -> {
            adotante.setNome(dadosNovos.getNome());
            adotante.setTelefone(dadosNovos.getTelefone());
            adotante.setEmail(dadosNovos.getEmail());
            return repository.save(adotante);
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
                .body("Este adotante possui histórico de adoção e não pode ser removido.");
        }
    }
}