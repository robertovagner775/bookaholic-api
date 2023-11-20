package com.bookaholic.backend.controller;

import java.util.List;

import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookaholic.backend.model.Editora;
import com.bookaholic.backend.model.ErrorResponse;
import com.bookaholic.backend.model.Escritor;
import com.bookaholic.backend.model.Livro;
import com.bookaholic.backend.model.testeDto;
import com.bookaholic.backend.model.testeDto;
import com.bookaholic.backend.repository.EditoraRepository;
import com.bookaholic.backend.repository.EscritorRepository;
import com.bookaholic.backend.repository.LivroRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RestController
@RequestMapping("/gerencia")
public class GerenciaController {

    @Autowired
    private EscritorRepository escritorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

     

    @PostMapping("/livro")
    public ResponseEntity<Livro> addLivro(@RequestBody testeDto dtolivro) {
        Escritor escritor = escritorRepository.findById(dtolivro.escritor_id()).get();
        Editora editora = editoraRepository.findById(dtolivro.editora_id()).get();
        Livro livro = new Livro(null, dtolivro.titulo(), dtolivro.sinopse(), dtolivro.data_lancamento(), escritor, editora, null);
        Livro novoLivro = livroRepository.save(livro);
        return ResponseEntity.status(201).body(novoLivro);
    }

 
    @GetMapping("/listLivro")
    public List<Livro> listAllLivro() {
        return livroRepository.findAll();
    }

    @GetMapping("/listLivroBy")
    public List<Livro> listByidEpub(@RequestParam("id") Long id) {
        return livroRepository.listLivroById(id);
        
    }

    @PostMapping("/escritor")
    public ResponseEntity<?> addEscritor(@RequestBody Escritor escritor) {
        escritorRepository.save(escritor);
      
        return ResponseEntity.ok().body(escritor);
    } 


    @PostMapping("/editora")
    public ResponseEntity<?> addEditora(@RequestBody Editora editora){
      //  if(editoraRepository.existsByname(editora.getNome_editora())){
         //   return   ResponseEntity.badRequest().body(new ErrorResponse(0, "name exists in database", "not permited duplicate name"));
        //}
        Editora newEditora = editoraRepository.save(editora);
        return ResponseEntity.ok().body(editora);

    }
    
    @GetMapping("/listNameEscritor")
    public List<Escritor> listNameEscritor() {
        return escritorRepository.findAll();
    }
    
    @GetMapping("/listNameEditora")
    public List<Editora> listNameEditora() {
        return editoraRepository.findAll();
    }
    

}
