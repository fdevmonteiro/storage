package com.example.estoque.controller;

import com.example.estoque.model.Produto;
import com.example.estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.listarTodos();
        model.addAttribute("produtos", produtos);
        return "produtos";
    }


    @GetMapping("/produtos/novo")
    public String mostrarFormularioDeCadastro(Model model) {
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "formulario_produto";
    }

    @PostMapping("/produtos")
    public String salvarProduto(@ModelAttribute("produto") Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String mostrarFormularioDeEdicao(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("produto", produto);
        return "formulario_produto";
    }

    @GetMapping("/produtos/deletar/{id}")
    public String deletarProduto(@PathVariable("id") Long id) {
        produtoService.deletarPorId(id);
        return "redirect:/produtos";
    }


}

