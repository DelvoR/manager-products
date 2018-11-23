package br.com.mateus.manager.products.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Loja implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4879101698489057083L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "loja_sequence")
	private Long id;
	@Column(name = "razao_social")
	private String razaoSocial;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "loja_fk"))
	private Endereco endereco;
	@ManyToMany
	private List<Produto> produtos;
	private String cnpj;

	public Loja() {

	}

	public Loja(String razaoSocial, Endereco endereco, String cnpj) {
		this.razaoSocial = razaoSocial;
		this.endereco = endereco;
		this.cnpj = cnpj;
	}
}
