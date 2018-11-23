package br.com.mateus.manager.products.model.entity;

import br.com.mateus.manager.products.model.enums.Estado;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Endereco implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "endereco_sequence")
	private Long id;
	private String rua;
	private Integer numero;
	private String bairro;
	private String cep;
	private String complemento;
	private String cidade;
	@Enumerated(EnumType.STRING)
	private Estado uf;

	public Endereco() {
	}

	private Endereco(Builder builder) {
		rua = builder.rua;
		numero = builder.numero;
		bairro = builder.bairro;
		cep = builder.cep;
		complemento = builder.complemento;
		cidade = builder.cidade;
	}

	public static class Builder {

		private String rua;
		private Integer numero;
		private String bairro;
		private String cep;
		private String complemento;
		private String cidade;
		private Estado uf;

		public Builder rua(String rua) {
			this.rua = rua;
			return this;
		}

		public Builder numero(Integer numero) {
			this.numero = numero;
			return this;
		}

		public Builder bairro(String bairro) {
			this.bairro = bairro;
			return this;
		}

		public Builder cep(String cep) {
			this.cep = cep;
			return this;
		}

		public Builder complemento(String complemento) {
			this.complemento = complemento;
			return this;
		}

		public Builder cidade(String cidade) {
			this.cidade = cidade;
			return this;
		}

		public Builder uf(Estado uf) {
			this.uf = uf;
			return this;
		}

		public Endereco build() {
			return new Endereco(this);
		}
	}
}
