package viva_animais.adocoes_spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "adotantes")
public class Adotante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do adotante é obrigatório")
    //@Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
    private String nome;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(
        regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$|^\\(?\\d{2}\\)?\\s?\\d{3,4}-?\\d{4}$",
        message = "Telefone deve estar em formato válido (ex: (11) 99999-9999 ou (11) 3333-4444)"
    )
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido (ex: usuario@exemplo.com)")
    @Column(unique = true, nullable = false)
    private String email;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}