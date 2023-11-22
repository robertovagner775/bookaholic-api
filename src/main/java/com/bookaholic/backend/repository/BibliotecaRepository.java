package com.bookaholic.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookaholic.backend.dto.BiblioDto;
import com.bookaholic.backend.model.Biblioteca;
import com.bookaholic.backend.model.LivroDto;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

   @Query(nativeQuery = true, value = "SELECT  COUNT(*)   FROM biblioteca WHERE id_imagem = :idImagem AND id_usuario = :idUser ")
   Long existsByUserImagem(Long idImagem, Long idUser);

   //String titulo, String subtitulo, String sinopsem , String pathImagem, String pathEpub, String status

   @Query(" SELECT new com.bookaholic.backend.dto.BiblioDto(b.id_biblioteca id, l.titulo , l.escritor.nome subtitulo, l.sinopse sinopse, m.nome_imagem pathImagem, e.nome_arquivo pathEpub, b.status)  FROM biblioteca b JOIN b.imagem m JOIN m.id_livro l  JOIN l.epub  e WHERE b.usuario.id = :id ")
   List<BiblioDto> viewBiblioteca(Long id);

   
   //@Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.path path_imagem, e.path path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub e WHERE l.titulo LIKE %:title% Group BY l.id_livro")
   //List<LivroDto> findAllByTitle(String title);


} 