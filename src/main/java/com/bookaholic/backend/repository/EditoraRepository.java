package com.bookaholic.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookaholic.backend.model.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long>{
    
    //Boolean existsByname(String nome_editora);
}
