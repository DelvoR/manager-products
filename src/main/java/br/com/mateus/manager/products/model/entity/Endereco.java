package br.com.mateus.manager.products.model.entity;

import br.com.mateus.manager.products.model.enums.Estado;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String rua;
	private Integer numero;
	private String bairro;
	private String cep;
	private String complemento;
	private String cidade;
	@Enumerated(EnumType.STRING)
	private Estado uf;

	Endereco() {

	}

	public Endereco(String rua, Integer numero, String bairro, String cep, String complemento, String cidade, Estado uf) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
	}

}
