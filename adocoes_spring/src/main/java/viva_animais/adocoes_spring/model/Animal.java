package viva_animais.adocoes_spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "animais")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do animal é obrigatório")
    //@Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Espécie é obrigatória")
    //@Size(min = 2, max = 50, message = "Espécie deve ter entre 2 e 50 caracteres")
    private String especie;

    @NotBlank(message = "Raça é obrigatória")
    //@Size(min = 2, max = 50, message = "Raça deve ter entre 2 e 50 caracteres")
    private String raca;

    @NotBlank(message = "Descrição é obrigatória")
    //@Size(min = 10, max = 500, message = "Descrição deve ter entre 10 e 500 caracteres")
    private String descricao;

    @NotNull(message = "Disponibilidade é obrigatória")
    private Integer disponivel; // 0 ou 1

    
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
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(int b) {
		this.disponivel = b;
	}
    
    
}
