package br.com.mateus.manager.products.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("persistence-default");

	private EntityManager entityManager;

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
