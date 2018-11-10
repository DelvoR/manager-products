package br.com.mateus.manager.products.utils;

public enum ColumnTitle {

    ID("id"), CNPJ("cnpj"), RAZAO_SOCIAL("Raz\u00E3o Social"), CIDADE("cidade"), BAIRRO("bairro");

    private String columnTitle;

    ColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

}
