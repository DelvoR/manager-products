package br.com.mateus.manager.products.model.entity;

import br.com.mateus.manager.products.model.enums.Estado;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Uma tabela no banco de dados para guardar informa&ccedil;&otilde;es espec&iacute;ficas desse
 * objeto. Tamb&eacute;m est&aacute; mapeada pelo Hibernate com a implementa&ccedil;&atilde;o JPA.
 */
@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3662464678421388074L;

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
		uf = builder.uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getUf() {
		return uf;
	}

	public void setUf(Estado uf) {
		this.uf = uf;
	}

	/**
	 * Builder de Endereco. Permite criar um Objeto Endere&ccedil;o com os atributos que
	 * forem necess&aacute;rios
	 */
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

		public Builder uf(String uf) {
			this.uf = Estado.fromString(uf);
			return this;
		}

		public Endereco build() {
			return new Endereco(this);
		}
	}
}
