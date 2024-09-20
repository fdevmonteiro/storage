package com.example.estoque.model;

import com.example.estoque.model.Produto;
import com.example.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jakarta.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class XMLParser {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void parseAndSaveProdutos(String filePath) {
        try {
            Document document = parseXMLFile(filePath);
            NodeList produtoList = document.getElementsByTagName("prod");

            for (int i = 0; i < produtoList.getLength(); i++) {
                Node produtoNode = produtoList.item(i);

                if (produtoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element produtoElement = (Element) produtoNode;
                    Produto produto = createProdutoFromElement(produtoElement);

                    // Salvar no banco de dados
                    produtoRepository.save(produto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Erro ao processar o arquivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Document parseXMLFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    private Produto createProdutoFromElement(Element produtoElement) {
        String nome = getTagValue("xProd", produtoElement);
        String quantidade = getTagValue("qCom", produtoElement);
        BigDecimal preco = new BigDecimal(getTagValue("vUnCom", produtoElement));

        return new Produto(nome, quantidade, preco);
    }

    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}




