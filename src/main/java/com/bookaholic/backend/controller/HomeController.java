package com.bookaholic.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookaholic.backend.model.Imagem;
import com.bookaholic.backend.model.LivroDto;
import com.bookaholic.backend.repository.ImagemRepository;
import com.bookaholic.backend.repository.LivroRepository;

@RestController
@RequestMapping("/home")
public class HomeController {
    
    @Autowired
    public LivroRepository livroRepository;

    @Autowired
    private ImagemRepository imagemRepository;
    
    @GetMapping("/livros")
    public List<LivroDto> viewLivros() {
        return imagemRepository.findAllJoinBook();
    }

    @GetMapping(value = "/livroTitulo")
    public List<LivroDto> viewTitulo(@RequestParam("titulo") String titulo) {
        return imagemRepository.findAllByTitle(titulo);
    }

    @GetMapping("/livro")
    public LivroDto viewLivroAvaliacao(@RequestParam("id") Long id) {
        return imagemRepository.findAllByIdBook(id);
    }

    @GetMapping("/livroAlfabetica")
    public List<LivroDto> viewLivroAlfabetica() {
        return imagemRepository.findAllJoinBookDois();
    }
     
    @GetMapping("/categoria")
    public List<LivroDto> viewLivroCategoria(@RequestParam("title") String title) {
        return imagemRepository.findAllByBook(title);
    }
}
