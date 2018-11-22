package br.com.mateus.manager.products.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
