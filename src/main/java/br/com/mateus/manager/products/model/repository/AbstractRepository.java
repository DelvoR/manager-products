package br.com.mateus.manager.products.model.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mateus.manager.products.connection.ConnectionFactory;

public abstract class AbstractRepository<Entity> {

	protected static EntityManager obterConexao() {
		return ConnectionFactory.getEntityManager();
	}

	public abstract boolean save(Entity entity);

	public abstract Entity findById(Long id);

	public abstract List<Entity> findAll();

	public abstract boolean remove(Long id);
}
