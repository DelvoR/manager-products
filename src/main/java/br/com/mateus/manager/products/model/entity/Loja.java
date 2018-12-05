package br.com.mateus.manager.products.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Loja implements Serializable {

	private static final long serialVersionUID = -4879101698489057083L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "loja_sequence")
	private Long id;

	@Column(name = "razao_social")
	private String razaoSocial;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@OneToMany(mappedBy = "loja", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Produto> produtos;

	private String cnpj;

	public Loja() {

	}

	public Loja(String razaoSocial, Endereco endereco, String cnpj) {
		this.razaoSocial = razaoSocial;
		this.endereco = endereco;
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
