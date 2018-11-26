package br.com.mateus.manager.products.controller.impl;

import br.com.mateus.manager.products.controller.IController;
import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.model.repository.AbstractRepository;
import br.com.mateus.manager.products.model.repository.impl.ProdutoRepository;

import java.util.List;

public class ProdutoController implements IController<Produto> {

    private AbstractRepository<Produto> repository;

    public ProdutoController() {
        repository = new ProdutoRepository();
    }

    @Override
    public boolean cadastrar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public boolean atualizar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public Produto buscar(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public boolean excluir(Long id) {
        return repository.remove(id);
    }

}