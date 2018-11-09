package br.com.mateus.manager.products.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "produto_sequence")
    private Long id;
    private String descricao;
    private Double quantidade;
    private Double valor;
    @ManyToMany(mappedBy = "produtos")
    private List<Loja> lojas;

    public Produto() {
    }

    public Produto(String descricao, Double quantidade, Double valor) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
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

}
