package br.com.mateus.manager.products.controller;

import java.util.List;

public interface IController<Entity> {

	boolean cadastrar(Entity entity);

	boolean atualizar(Entity entity);

    Entity buscar(Long id);

	List<Entity> buscarTodos();

	boolean excluir(Long id);

}
