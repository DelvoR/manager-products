package br.com.mateus.manager.products.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = -52245413660369513L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "categoria_sequence")
	private Long id;
	private String tipo;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "categoria_fk"))
	private SubCategoria subCategoria;

}
