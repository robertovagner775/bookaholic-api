package com.bookaholic.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookaholic.backend.dto.ListAvalUserDto;
import com.bookaholic.backend.dto.SelecAvalDto;
import com.bookaholic.backend.model.Avaliacao;
import com.bookaholic.backend.model.LivroDto;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {


    @Query("SELECT new com.bookaholic.backend.dto.SelecAvalDto( AVG(a.qtd_estrela) score, COUNT(*) quantidade, l.titulo )  FROM avaliacao a JOIN a.livro l WHERE l.id_livro = :id Group BY l.id_livro")
    SelecAvalDto findByAvalId(Long id);
    
    @Query("SELECT new com.bookaholic.backend.dto.ListAvalUserDto(a.qtd_estrela, u.username nome_usuario, a.descricao descricao, a.created  created) FROM avaliacao a JOIN a.usuario u  JOIN a.livro l WHERE l.id_livro = :id")
    List<ListAvalUserDto> findByLivro_id(Long id);

    @Query("SELECT new com.bookaholic.backend.dto.SelecAvalDto( AVG(a.qtd_estrela) as score, COUNT(*) as quantidade, l.titulo )  FROM avaliacao a JOIN a.livro l  Group BY l.id_livro, l.titulo ORDER BY score DESC  LIMIT 2")
    List<SelecAvalDto> findByScoreSemanal();

    //@Query(" SELECT new com.bookaholic.backend.model.LivroDto(l.id_livro , m.idImagem,l.titulo, l.sinopse, l.editora , l.escritor, m.path path_imagem, e.path path_epub)  FROM imagem m JOIN m.id_livro l JOIN l.epub e WHERE l.id_livro = :id Group BY l.id_livro")
}
