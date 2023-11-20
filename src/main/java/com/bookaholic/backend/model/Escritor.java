package com.bookaholic.backend.model;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "escritor")
@Table(name = "escritor")
public class Escritor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_escritor;

    private String sobre_autor;

    @Column(name = "nome")
    private String nome;

    @Temporal(TemporalType.DATE) 
    private Date data_nascimento;

    
}
