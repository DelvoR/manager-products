package br.com.mateus.manager.products.controller;

import br.com.mateus.manager.products.exceptions.AtualizarException;
import br.com.mateus.manager.products.exceptions.BuscarException;
import br.com.mateus.manager.products.exceptions.CadastrarException;
import br.com.mateus.manager.products.exceptions.ExcluirException;

import java.util.List;

public interface IController<Entity> {

	void cadastrar(Entity entity) throws CadastrarException;

	void atualizar(Entity entity) throws AtualizarException;

	Entity buscar(Long id) throws BuscarException;

	List<Entity> buscarTodos() throws BuscarException;

	void excluir(Long id) throws ExcluirException;

}
