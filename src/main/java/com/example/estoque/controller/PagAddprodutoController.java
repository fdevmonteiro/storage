package com.example.estoque.controller;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.estoque.model.Produto;
import com.example.estoque.service.ProdutoService;





@Controller
public class PagAddprodutoController{
	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/adicionar")
	public String showAddProdutoForm(Model model) {
		System.out.println("Acessando a página de adicionar produto");
		model.addAttribute("produto", new Produto());
		return "adicionar-produto";
	}

	@PostMapping("/adicionar")
	public String addProduto(@ModelAttribute("produto") Produto produto, RedirectAttributes redirectAttributes) {
		produtoService.salvar(produto);
		redirectAttributes.addFlashAttribute("message", "Produto adicionado com sucesso!");
		return "redirect:/produtos";
	}

       @GetMapping("upload")
	public String showUploadForm(Model model) {
		return "upload";

	   }
}

