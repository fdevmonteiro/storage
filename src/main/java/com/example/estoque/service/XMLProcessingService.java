package com.example.estoque.service;

import com.example.estoque.model.Produto;
import com.example.estoque.repository.ProdutoRepository;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class XMLProcessingService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void processarArquivoXML(String filePath) {
        try {
            // Criar o contexto JAXB e o deserializador
            JAXBContext jaxbContext = JAXBContext.newInstance(Produto.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Ler o arquivo XML
            File file = new File(filePath);
            Produto produto = (Produto) unmarshaller.unmarshal(file);

            // Salvar o produto no banco de dados
            produtoRepository.save(produto);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
