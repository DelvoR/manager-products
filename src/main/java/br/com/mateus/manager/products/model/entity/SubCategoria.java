package br.com.mateus.manager.products.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class SubCategoria implements Serializable {

	private static final long serialVersionUID = -8884046566332822724L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "subcategoria_sequence")
	private Long id;
	private String tipo;

}
