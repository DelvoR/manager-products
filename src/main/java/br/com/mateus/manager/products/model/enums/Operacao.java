package br.com.mateus.manager.products.model.enums;

public enum Operacao {

	NOVO(1),
	EDITAR(2),
	EXCLUIR(3);

	private final Integer operacao;

	Operacao(Integer operacao) {
		this.operacao = operacao;
	}

	public Integer getOperacao() {
		return operacao;
	}
}
