package viva_animais.adocoes_spring.model;

import java.sql.Date;

public class AdocaoDetalhadaDTO {
    private Long adocaoId;
    private Long adotanteId;
    private String nomeAdotante;
    private String telefone;
    private String email;
    private String nomeAnimal;
    private Date data;
    private String status;

    public AdocaoDetalhadaDTO(Long adocaoId, Long adotanteId, String nomeAdotante, String telefone, String email, String nomeAnimal, Date data, String status) {
        this.adocaoId = adocaoId;
        this.adotanteId = adotanteId;
        this.nomeAdotante = nomeAdotante;
        this.telefone = telefone;
        this.email = email;
        this.nomeAnimal = nomeAnimal;
        this.data = data;
        this.status = status;
    }

	public Long getAdocaoId() {
		return adocaoId;
	}

	public void setAdocaoId(Long adocaoId) {
		this.adocaoId = adocaoId;
	}
	
	public Long getAdotanteId() {
		return adotanteId;
	}

	public void setAdotanteId(Long adontanteId) {
		this.adotanteId = adontanteId;
	}

	public String getNomeAdotante() {
		return nomeAdotante;
	}

	public void setNomeAdotante(String nomeAdotante) {
		this.nomeAdotante = nomeAdotante;
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

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
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