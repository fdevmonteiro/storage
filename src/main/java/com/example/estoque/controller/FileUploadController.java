package com.example.estoque.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    private final String uploadDirectory = "C:/Users/kauad/Documents/arquivosImport";  // Substitua pelo caminho do diretório desejado

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("xmlFile") MultipartFile file) {
        if (file.isEmpty()) {
            return "O arquivo está vazio.";
        }

        try {
            // Defina o caminho completo para onde o arquivo será salvo
            Path filePath = Paths.get(uploadDirectory, file.getOriginalFilename());
            file.transferTo(new File(filePath.toString()));

            return "Arquivo carregado com sucesso: " + filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao carregar o arquivo.";
        }
    }
}
