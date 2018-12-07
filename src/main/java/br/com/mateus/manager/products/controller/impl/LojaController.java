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

/**
 * Define os m&eacute;todos capazes de produzir os eventos da interface de usu&aacute;rio,
 * transforma esses eventos em a&ccedil;&otilde;es no modelo. As a&ccedil;&otilde;es de usu&aacute;rio na tela
 * LojaView s&atilde;o atendidos pelos m&eacute;todos definidos pela classe controlador.
 */
public class LojaController implements IController<Loja> {

	private AbstractRepository<Loja> repository;

	/**
	 *
	 */
	public LojaController() {
		repository = new LojaRepository();
		((LojaRepository) repository).setEntityManager(ConnectionFactory.getEntityManager());
	}

	/**
	 * @param loja
	 * @throws CadastrarException
	 */
	@Override
	public void cadastrar(Loja loja) throws CadastrarException {
		try {
			repository.save(loja);
		} catch (Exception e) {
			throw new CadastrarException("Erro ao cadastrar loja", e);
		}
	}

	/**
	 * @param loja
	 * @throws AtualizarException
	 */
	@Override
	public void atualizar(Loja loja) throws AtualizarException {
		try {
			repository.update(loja);
		} catch (Exception e) {
			throw new AtualizarException("Erro ao atualizar loja", e);
		}
	}

	/**
	 * @param id
	 * @return
	 * @throws BuscarException
	 */
	@Override
	public Loja buscar(Long id) throws BuscarException {
		Loja loja = repository.findById(id);
		if (loja == null) {
			throw new BuscarException("Loja nao encontrado");
		}
		return loja;
	}

	/**
	 * @return
	 * @throws BuscarException
	 */
	@Override
	public List<Loja> buscarTodos() throws BuscarException {
		List<Loja> lojas = repository.findAll();
		if (CollectionUtils.isEmpty(lojas)) {
			throw new BuscarException("Loja nao encontrado");
		}
		return lojas;
	}

	/**
	 * @param id
	 * @throws ExcluirException
	 */
	@Override
	public void excluir(Long id) throws ExcluirException {
		try {
			repository.remove(id);
		} catch (Exception e) {
			throw new ExcluirException("Erro ao excluir loja", e);
		}
	}

}
