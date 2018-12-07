package br.com.mateus.manager.products.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Delvo Servi&ccedil;o central para todas as a&ccedil;&otilde;es de persist&ecirc;ncia. Entidades s&atilde;o
 * objetos de Java que s&atilde;o alocados como qualquer outro objeto Java. Eles n&atilde;o
 * ficam persistentes explicitamente at&eacute; seu c&oacute;digo interagir com o
 * EntityManager para o fazer persistente. O EntityManager administra o ORM que
 * o mapea entre uma classe de entidade e uma fonte de dados subjacente. O
 * EntityManager prov&ecirc; APIs para criar consultas, buscar objetos, sincronizar
 * objetos e inserir objetos no banco de dados. Um EntityManager mapea um
 * conjunto de classes e um banco de dados particular. Esse conjunto de classes
 * &eacute; chamado de persistence unit (unidade de persist&ecirc;ncia). Essa unidade de
 * persist&ecirc;ncia est&aacute; definida em um arquivo em
 * src/main/resources/META-INF/persistence.xml. &Eacute; um arquivo descritor de
 * desenvolvimento exigido no JPA. Ele define uma ou mais unidades de
 * persist&ecirc;ncia. Todas as classes de entidades est&atilde;o especificas em
 * persistence.xml. Um EntityManagerFactory fornece inst&acirc;ncias de um
 * EntityManager *
 *
 * @since 09/11/2018
 */
public class ConnectionFactory {

	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("persistence-default");

	/**
	 * Obt&eacute;m EntityManager a partir do Factory
	 *
	 * @return EntityManager
	 */
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
