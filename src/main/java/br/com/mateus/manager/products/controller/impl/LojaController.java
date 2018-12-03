package br.com.mateus.manager.products.controller.impl;

import java.util.List;

import br.com.mateus.manager.products.controller.IController;
import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.repository.AbstractRepository;
import br.com.mateus.manager.products.model.repository.impl.LojaRepository;

public class LojaController implements IController<Loja> {

    private AbstractRepository<Loja> repository;

    public LojaController() {
        repository = new LojaRepository();
    }

    @Override
    public boolean cadastrar(Loja loja) {
        return repository.save(loja);
    }

    @Override
    public boolean atualizar(Loja loja) {
        return repository.save(loja);
    }

    @Override
    public Loja buscar(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Loja> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public boolean excluir(Long id) {
        return repository.remove(id);
    }

}
