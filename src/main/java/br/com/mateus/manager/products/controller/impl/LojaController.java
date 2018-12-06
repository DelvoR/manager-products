package br.com.mateus.manager.products.controller.impl;

import br.com.mateus.manager.products.connection.ConnectionFactory;
import br.com.mateus.manager.products.controller.IController;
import br.com.mateus.manager.products.exceptions.AtualizarException;
import br.com.mateus.manager.products.exceptions.BuscarException;
import br.com.mateus.manager.products.exceptions.CadastrarException;
import br.com.mateus.manager.products.exceptions.ExcluirException;
import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.repository.AbstractRepository;
import br.com.mateus.manager.products.model.repository.impl.LojaRepository;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class LojaController implements IController<Loja> {

	private AbstractRepository<Loja> repository;

	public LojaController() {
		repository = new LojaRepository();
		((LojaRepository) repository).setEntityManager(ConnectionFactory.getEntityManager());
	}

	@Override
	public void cadastrar(Loja loja) throws CadastrarException {
		try {
			repository.save(loja);
		} catch (Exception e) {
			throw new CadastrarException("Erro ao cadastrar loja", e);
		}
	}

	@Override
	public void atualizar(Loja loja) throws AtualizarException {
		try {
			repository.update(loja);
		} catch (Exception e) {
			throw new AtualizarException("Erro ao atualizar loja", e);
		}
	}

	@Override
	public Loja buscar(Long id) throws BuscarException {
		Loja loja = repository.findById(id);
		if (loja == null) {
			throw new BuscarException("Loja nao encontrado");
		}
		return loja;
	}

	@Override
	public List<Loja> buscarTodos() throws BuscarException {
		List<Loja> lojas = repository.findAll();
		if (CollectionUtils.isEmpty(lojas)) {
			throw new BuscarException("Loja nao encontrado");
		}
		return lojas;
	}

	@Override
	public void excluir(Long id) throws ExcluirException {
		try {
			repository.remove(id);
		} catch (Exception e) {
			throw new ExcluirException("Erro ao excluir loja", e);
		}
	}

}
