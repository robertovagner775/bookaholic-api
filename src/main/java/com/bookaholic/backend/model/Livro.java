package com.bookaholic.backend.model;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "livro")
@Entity(name = "livro")
public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long id_livro;

    private String titulo;

    private String sinopse;

    private Date data_lancamento;

    @ManyToOne(targetEntity = Escritor.class)
    @JoinColumn(nullable = false, name = "escritor_id")
    private Escritor escritor;

    @ManyToOne(targetEntity = Editora.class)
    @JoinColumn(nullable = false, name = "editora_id")
    private Editora editora;

    @ManyToOne(targetEntity = Epub.class)
    @JoinColumn(nullable = true, name = "epub_id")
    private Epub epub;

    



}
