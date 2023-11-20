package com.bookaholic.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.bookaholic.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM usuario where email = :email")
    Usuario findByEmailQuery(String email);

    @Query(nativeQuery = true,value = "SELECT * FROM usuario where username = :username")
    Usuario findByUsername(String username);

    Boolean existsByEmail (String email) ;

    UserDetails findByEmail(String email);
    
}
