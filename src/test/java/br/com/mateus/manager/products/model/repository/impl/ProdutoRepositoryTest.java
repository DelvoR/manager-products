package br.com.mateus.manager.products.model.repository.impl;

import br.com.mateus.manager.products.model.entity.Produto;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProdutoRepositoryTest {

	@Test
	public void testDeveBuscarProdutoPorId() {
		Produto prod = new Produto();
		prod.setId(1L);
		prod.setDescricao("Teste");
		prod.setQuantidade(10.0);
		prod.setValor(5.0);
		EntityManager entityManager = mock(EntityManager.class);
		when(entityManager.find(Produto.class, 1L)).thenReturn(prod);

		ProdutoRepository repository = new ProdutoRepository();
		repository.setEntityManager(entityManager);

		Produto produto = repository.findById(1L);
		assertEquals("Teste", produto.getDescricao());
	}

}