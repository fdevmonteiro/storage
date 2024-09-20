package com.example.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MovimentacaoController{

    @GetMapping("/movimentacao")

        public String pagMovimentacao(){

            return "movimentacao_pagina";

        }


    }


