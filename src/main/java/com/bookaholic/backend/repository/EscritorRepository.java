package com.bookaholic.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookaholic.backend.model.Escritor;
import com.bookaholic.backend.model.Usuario;

public interface EscritorRepository extends JpaRepository<Escritor, Long> {

    /**
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT id_escritor, nome FROM escritor")
    List<Escritor> findAllNameIdEList();
    
}
