package com.example.estoque.controller;



import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaListaController{
	
	@GetMapping("/lista")
		
		public String showListaPage() {
			return "lista";			
		}
	
}


	
