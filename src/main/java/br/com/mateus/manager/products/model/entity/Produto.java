package br.com.mateus.manager.products.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Produto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -602935078438200968L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "produto_sequence")
    private Long id;
    private String descricao;
    private Double quantidade;
    private Double valor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "produto_fk"))
    private Categoria categoria;
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
