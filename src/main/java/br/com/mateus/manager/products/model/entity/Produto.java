package br.com.mateus.manager.products.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Produto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -602935078438200968L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "produto_sequence")
	private Long id;
	private String descricao;
	private Double quantidade;
	private Double valor;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(foreignKey = @ForeignKey(name = "produto_fk"))
//	private Categoria categoria;
	@ManyToMany(mappedBy = "produtos")
	private List<Loja> lojas;

	public Produto() {
	}

	public Produto(Builder builder) {
		descricao = builder.descricao;
		quantidade = builder.quantidade;
		valor = builder.valor;
//		categoria = builder.categoria;
		lojas = builder.lojas;
	}

	@SuppressWarnings("unused")
	public static class Builder {
		private String descricao;
		private Double quantidade;
		private Double valor;
//		private Categoria categoria;
		private List<Loja> lojas;

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

//		public Builder categoria(Categoria categoria) {
//			this.categoria = categoria;
//			return this;
//		}

//		public Builder subCategoria(SubCategoria subCategoria) {
//			this.categoria.setSubCategoria(subCategoria);
//			return this;
//		}

		public Builder loja(Loja loja) {
			if (this.lojas == null) {
				this.lojas = new ArrayList<>();
			}
			this.lojas.add(loja);
			return this;
		}

		public Produto build() {
			return new Produto(this);
		}
	}
}
