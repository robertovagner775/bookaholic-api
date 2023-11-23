package com.bookaholic.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "avaliacao")
@Table(name = "avaliacao")
public class Avaliacao {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP) 
    private Date created;

    private int qtd_estrela;
    private String descricao;

    @ManyToOne(targetEntity = Livro.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_livro")
    private Livro livro;


    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario usuario;


    
    public Avaliacao( int qtd_estrela, String descricao, Livro livro, Usuario usuario){
        this.created = new Date();
        this.qtd_estrela = qtd_estrela;
        this.descricao = descricao; 
        this.livro = livro;
        this.usuario = usuario;
    }


}
