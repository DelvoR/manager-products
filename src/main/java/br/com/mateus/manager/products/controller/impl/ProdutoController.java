package br.com.mateus.manager.products.controller.impl;

import java.util.List;

import br.com.mateus.manager.products.controller.IController;
import br.com.mateus.manager.products.model.entity.Produto;

public class ProdutoController implements IController<Produto> {

	@Override
	public boolean cadastrar(Produto produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atualizar(Produto produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long getId(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}