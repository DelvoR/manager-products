package br.com.mateus.manager.products.model.repository;

import br.com.mateus.manager.products.connection.ConnectionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractRepository<Entity> {

	protected static EntityManager obterConexao() {
		return ConnectionFactory.getEntityManager();
	}

	public abstract void save(Entity entity) throws Exception;

	public abstract void update(Entity entity);

	public abstract Entity findById(Long id);

	public abstract List<Entity> findAll();

	public abstract void remove(Long id);
}
