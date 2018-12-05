package br.com.mateus.manager.products.model.enums;

import org.apache.commons.lang3.StringUtils;

public enum Estado {
	RO(11, "Rond\u00f4nia", "Rondonia"),
	AC(12, "Acre"),
	AM(13, "Amazonas"),
	RR(14, "Roraima"),
	PA(15, "Par\u00e1", "Para"),
	AP(16, "Amap\u00e1", "Amapa"),
	TO(17, "Tocantins"),
	MA(21, "Maranh\u00e3o", "Maranhao"),
	PI(22, "Piau\u00ed", "Piaui"),
	CE(23, "Cear\u00e1"),
	RN(24, "Rio Grande do Norte"),
	PB(25, "Para\u00edba", "Paraiba"),
	PE(26, "Pernambuco"),
	AL(27, "Alagoas"),
	SE(28, "Sergipe"),
	BA(29, "Bahia"),
	MG(31, "Minas Gerais"),
	ES(32, "Esp\u00edrito Santo", "Espirito Santo"),
	RJ(33, "Rio de Janeiro"),
	SP(35, "S\u00e3o Paulo", "Sao Paulo"),
	PR(41, "Paran\u00e1", "Parana"),
	SC(42, "Santa Catarina"),
	RS(43, "Rio Grande do Sul"),
	MS(50, "Mato Grosso do Sul"),
	MT(51, "Mato Grosso"),
	GO(52, "Goi\u00e1s", "Goias"),
	DF(53, "Distrito Federal");

	private final int codigoIbge;
	private final String nome;
	private String nomeSemAcentuacao;

	Estado(int codigoIbge, String nome) {
		this.codigoIbge = codigoIbge;
		this.nome = nome;
	}

	Estado(int codigoIbge, String nome, String nomeSemAcentuacao) {
		this(codigoIbge, nome);
		if (StringUtils.isNotBlank(nomeSemAcentuacao)) {
			this.nomeSemAcentuacao = nomeSemAcentuacao;
		} else {
			this.nomeSemAcentuacao = nome;
		}
	}

	public static Estado fromString(String sigla) {
		for (Estado estado : Estado.values()) {
			if (estado.name().equals(sigla)) {
				return estado;
			}
		}
		return null;
	}

	public String getCodigo() {
		return Integer.toString(codigoIbge);
	}

	public int getCodigoIbge() {
		return codigoIbge;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeSemAcentuacao() {
		return nomeSemAcentuacao;
	}

	public String getSigla() {
		return this.name();
	}

}
