package com.bookaholic.backend.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookaholic.backend.model.Livro;
import com.bookaholic.backend.model.LivroDto;

public interface LivroRepository  extends JpaRepository<Livro, Long>{

   // @Query(value = "SELECT new com.bookaholic.backend.model.LivroDto(l.titulo, l.sinopse, l.editora , l.escritor, e.path as path_epub, m.path as path_imagem) FROM livro as l JOIN imagem as m ON m.id_livro = l.id_livro  JOIN epub as e ON  e.livro = l.id ")
   // List<LivroDto> view_all_livro(); 

    @Query(nativeQuery = true, value = "UPDATE livro SET epub_id = :idEpub WHERE id_livro = :id_livro")
    public List<Livro> alterBookInEpub(Long idEpub, Long id_livro);


    @Query(nativeQuery = true, value = "SELECT * FROM livro as l INNER JOIN epub as e on l.epub_id = e.id_epub WHERE l.id_livro = :id")
    public List<Livro> listLivroById(Long id);

    
   
   // @Query("SELECT new com.bookaholic.backend.model.LivroDto(l.titulo, l.sinopse, l.escritor_id, l.editora_id, e.path as path_epub, m.path as path_imagem) FROM livro  l INNER JOIN imagem m on l.id = m.id_livro INNER JOIN epub e on e.id_livro = l.id")
}
