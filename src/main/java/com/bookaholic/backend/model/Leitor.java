package com.bookaholic.backend.model;

import java.util.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Table(name = "leitor")
@Entity(name = "leitor")
public class Leitor {


    public Leitor(String rg, String cpf, String nome_completo, Date data_nascimento, Usuario usuario) {
        this.RG = rg;
        this.CPF = cpf;
        this.nome_completo = nome_completo;
        this.data_nascimento = data_nascimento;
        this.usuario = usuario;

    }
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String RG;

    private String CPF;

    private String nome_completo;

    private Date data_nascimento;

    
    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuario usuario;

    
    
}
