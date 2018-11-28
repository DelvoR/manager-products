package br.com.mateus.manager.products.utils;

public enum ColumnTitle {

    ID("id"), 
    CNPJ("cnpj"), 
    RAZAO_SOCIAL("Raz\u00E3o Social"), 
    CIDADE("cidade"), 
    BAIRRO("bairro"),
    DESCRICAO("Descri\u00E7\u00E3o"),
    QUANTIDADE("quantidade"),
    PRECO("pre\u00E7o");

    private String columnTitle;

    ColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

}
