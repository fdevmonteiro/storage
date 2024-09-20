package com.example.estoque.repository;
import com.example.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoXMLRepository extends JpaRepository<Produto,Long>{
    Optional<Produto> findByNome(String nome);

}