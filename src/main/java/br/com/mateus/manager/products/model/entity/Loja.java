package br.com.mateus.manager.products.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Representa o objeto Loja. &Eacute; uma Entidade (tabela) no banco de dados. Os
 * atributos da classe representam as colunas da tabela loja no banco e &eacute; onde
 * ser&atilde;o persistidos os dados referente ao objeto Loja. A classe est&aacute; mapeada
 * com as anota&ccedil;&otilde;es fornecidas pela framework Hibernate. Com @Entity informamos
 * que a classe &eacute; uma tabela no banco. @Columm ser&atilde;o as colunas. Essas anota&ccedil;&otilde;es
 * s&atilde;o conven&ccedil;&otilde;es definidas no Hibernate. Para que isso aconte&ccedil;a &eacute; feito o
 * mapeamento desta classe no arquivo de persist&ecirc;ncia
 * src/main/resources/META-INF/persistence.xml O Framework Hibernate aqui &eacute;
 * respons&aacute;vel por abstrar o c&oacute;digo SQL, toda a camada JDBC e o SQL ser&aacute; gerado
 * em tempo de execu&ccedil;&atilde;o.
 */
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
