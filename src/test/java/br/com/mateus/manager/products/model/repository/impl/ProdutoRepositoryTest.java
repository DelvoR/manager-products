package br.com.mateus.manager.products.model.repository.impl;

import br.com.mateus.manager.products.model.entity.Produto;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
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

	@Test
	public void testDeveBuscarTodosProdutos() {
		List<Produto> produtos = new ArrayList<>();

		Produto prod1 = new Produto();
		prod1.setId(1L);
		prod1.setDescricao("Teste 1");
		prod1.setQuantidade(10.0);
		prod1.setValor(5.0);

		Produto prod2 = new Produto();
		prod1.setId(2L);
		prod1.setDescricao("Teste 2");
		prod1.setQuantidade(15.0);
		prod1.setValor(20.0);

		produtos.add(prod1);
		produtos.add(prod2);
		EntityManager entityManager = mock(EntityManager.class);
		Query query = mock(Query.class);
		when(query.getResultList()).thenReturn(produtos);
		when(entityManager.createQuery(anyString())).thenReturn(query);

		ProdutoRepository repository = new ProdutoRepository();
		repository.setEntityManager(entityManager);

		List<Produto> expected = repository.findAll();
		assertEquals(2, expected.size());
	}

	@Test
	public void testDeveSalvarProduto() {
		EntityManager entityManager = mock(EntityManager.class);
		ProdutoRepository repository = mock(ProdutoRepository.class);
		repository.setEntityManager(entityManager);

		Produto produto = new Produto();
		produto.setId(1L);
		produto.setDescricao("Teste");
		produto.setQuantidade(10.0);
		produto.setValor(5.0);

		when(repository.save(any(Produto.class))).thenReturn(produto);
		Produto produtoSalvo = repository.save(produto);

		assertEquals("Teste", produtoSalvo.getDescricao());
	}

}