package com.bookaholic.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "epub")
@Table(name = "epub")
public class Epub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEpub;


    private String extensao;
    
    private String nome_arquivo;

    private String path;

    private long tamanho;
    
 
    
}
