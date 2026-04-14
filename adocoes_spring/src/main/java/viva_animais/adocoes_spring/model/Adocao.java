package viva_animais.adocoes_spring.model;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data // O Lombok criará getAnimal() e getAdotante() automaticamente. se preferir, use getters e setters
@Table(name = "adocoes")
public class Adocao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adocoes_id;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "adotantes_id", nullable = false)
    private Adotante adotante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animais_id", nullable = false)
    private Animal animal;
    
    @NotNull(message = "A data é obrigatória")
    private Date data;
    private String status = "ATIVA"; 
    
	public Animal getAnimal() {
        return animal; 
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    public Adotante getAdotante() {
        return adotante; 
    }

    public void setAdotante(Adotante adotante) {
        this.adotante = adotante;
    }
    
    public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
	
