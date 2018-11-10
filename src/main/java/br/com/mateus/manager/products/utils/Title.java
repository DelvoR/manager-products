package br.com.mateus.manager.products.utils;

public enum Title {

    SUCESSO("Sucesso"), INFORMACAO("Informa\u00e7\u00e3o"), EXCLUIR("Excluir");

    private String titulo;

    Title(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

}
