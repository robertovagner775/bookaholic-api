package com.bookaholic.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class LivroDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idImagem;
    private String titulo;
    private String sinopse;

    
    @ManyToOne(targetEntity = Editora.class)
    @JoinColumn(nullable = false, name = "editora_id")
    private Editora editora;
    
    @ManyToOne(targetEntity = Escritor.class)
    @JoinColumn(nullable = false, name = "escritor_id")
    private Escritor escritor;
    



    private String path_imagem;

    private String path_epub;
}