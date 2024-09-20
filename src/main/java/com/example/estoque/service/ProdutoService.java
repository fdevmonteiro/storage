package com.example.estoque.service;

import com.example.estoque.model.Produto;
import com.example.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;




    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;

    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto produto) {
        Produto salvo = produtoRepository.save(produto);

        // Criar um registro de movimentação para o produto salvo

        return salvo;
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deletarPorId(Long id) {
        produtoRepository.deleteById(id);
    }



    public void salvarProduto(Produto produto) {
        produtoRepository.save(produto);
    }
}


