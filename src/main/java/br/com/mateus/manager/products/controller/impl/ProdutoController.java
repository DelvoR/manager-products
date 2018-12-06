package br.com.mateus.manager.products.controller.impl;

import br.com.mateus.manager.products.connection.ConnectionFactory;
import br.com.mateus.manager.products.controller.IController;
import br.com.mateus.manager.products.exceptions.AtualizarException;
import br.com.mateus.manager.products.exceptions.BuscarException;
import br.com.mateus.manager.products.exceptions.CadastrarException;
import br.com.mateus.manager.products.exceptions.ExcluirException;
import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.model.repository.AbstractRepository;
import br.com.mateus.manager.products.model.repository.impl.ProdutoRepository;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class ProdutoController implements IController<Produto> {

	private AbstractRepository<Produto> repository;

	public ProdutoController() {
		repository = new ProdutoRepository();
		((ProdutoRepository) repository).setEntityManager(ConnectionFactory.getEntityManager());
	}

	@Override
	public void cadastrar(Produto produto) throws CadastrarException {
		try {
			repository.save(produto);
		} catch (Exception e) {
			throw new CadastrarException("Erro ao cadastrar produto", e);
		}
	}

	@Override
	public void atualizar(Produto produto) throws AtualizarException {
		try {
			repository.update(produto);
		} catch (Exception e) {
			throw new AtualizarException("Erro ao atualizar produto", e);
		}
	}

	@Override
	public Produto buscar(Long id) throws BuscarException {
		Produto produto = repository.findById(id);
		if (produto == null) {
			throw new BuscarException("Produto nao encontrado");
		}
		return produto;
	}

	@Override
	public List<Produto> buscarTodos() throws BuscarException {
		List<Produto> produt = repository.findAll();
		if (CollectionUtils.isEmpty(produt)) {
			throw new BuscarException("Produto nao encontrado");
		}
		return produt;
	}

	@Override
	public void excluir(Long id) throws ExcluirException {
		try {
			repository.remove(id);
		} catch (Exception e) {
			throw new ExcluirException("Erro ao excluir produto", e);
		}
	}

}
