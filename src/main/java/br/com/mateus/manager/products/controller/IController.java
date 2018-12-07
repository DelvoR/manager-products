package br.com.mateus.manager.products.controller;

import br.com.mateus.manager.products.exceptions.AtualizarException;
import br.com.mateus.manager.products.exceptions.BuscarException;
import br.com.mateus.manager.products.exceptions.CadastrarException;
import br.com.mateus.manager.products.exceptions.ExcluirException;

import java.util.List;

/**
 * @param <Entity>
 */
public interface IController<Entity> {

	/**
	 * @param entity
	 * @throws CadastrarException
	 */
	void cadastrar(Entity entity) throws CadastrarException;

	/**
	 * @param entity
	 * @throws AtualizarException
	 */
	void atualizar(Entity entity) throws AtualizarException;

	/**
	 * @param id
	 * @return
	 * @throws BuscarException
	 */
	Entity buscar(Long id) throws BuscarException;

	/**
	 * @return
	 * @throws BuscarException
	 */
	List<Entity> buscarTodos() throws BuscarException;

	/**
	 * @param id
	 * @throws ExcluirException
	 */
	void excluir(Long id) throws ExcluirException;

}
