package com.bookaholic.backend.model;

import com.bookaholic.backend.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "biblioteca")
@Table(name =  "biblioteca")
public class Biblioteca {
    

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_biblioteca;

   
    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario usuario;


     @ManyToOne(targetEntity = Imagem.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_imagem")
    private Imagem imagem;


    private String status;
}
