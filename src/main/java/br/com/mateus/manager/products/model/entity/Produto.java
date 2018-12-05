package br.com.mateus.manager.products.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = -602935078438200968L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "produto_sequence")
	private Long id;
	private String descricao;
	private Double quantidade;
	private Double valor;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "loja_id")
	private Loja loja;

	public Produto() {
	}

	public Produto(Builder builder) {
		descricao = builder.descricao;
		quantidade = builder.quantidade;
		valor = builder.valor;
		loja = builder.loja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@SuppressWarnings("unused")
	public static class Builder {
		private String descricao;
		private Double quantidade;
		private Double valor;
		private Loja loja;

		public Builder descricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public Builder quantidade(Double quantidade) {
			this.quantidade = quantidade;
			return this;
		}

		public Builder valor(Double valor) {
			this.valor = valor;
			return this;
		}

		public Builder loja(Loja loja) {
			this.loja = loja;
			return this;
		}

		public Produto build() {
			return new Produto(this);
		}
	}
}
