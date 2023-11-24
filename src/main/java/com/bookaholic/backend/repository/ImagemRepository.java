package com.bookaholic.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.bookaholic.backend.model.Imagem;
import com.bookaholic.backend.model.LivroDto;

public interface ImagemRepository extends JpaRepository<Imagem, Long>{

   // @Query(value = "SELECT new com.bookaholic.backend.model.LivroDto(l.titulo, l.sinopse, l.editora , l.escritor, m.path path_imagem, e.path path_epub) FROM imagem  m JOIN m.livro l  INNER JOIN Epub.Livro e")
    //List<LivroDto> view_all_livro();

    @Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.nome_imagem path_imagem, e.nome_arquivo path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub e Group BY l.id_livro, m.idImagem")
    List<LivroDto> findAllJoinBook();

    
    @Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.nome_imagem path_imagem, e.nome_arquivo path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub  e   Group BY l.id_livro, m.idImagem ORDER BY l.titulo DESC")
    List<LivroDto> findAllJoinBookDois();

     @Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.nome_imagem path_imagem, e.nome_arquivo path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub e WHERE l.id_livro = :id Group BY l.id_livro, m.idImagem")
     LivroDto findAllByIdBook(Long id);

     @Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.nome_imagem path_imagem, e.nome_arquivo path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub e WHERE l.titulo LIKE %:title% Group BY l.id_livro, m.idImagem")
     List<LivroDto> findAllByTitle(String title);

     @Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.nome_imagem path_imagem, e.nome_arquivo path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub e WHERE l.titulo = :title OR l.titulo = :title2")
     List<LivroDto> findBySemanal(String title, String title2);

     @Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.nome_imagem path_imagem, e.nome_arquivo path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub e WHERE l.sinopse LIKE %:title% Group BY l.id_livro, m.idImagem")
     List<LivroDto> findAllByBook(String title);

     
    
}
