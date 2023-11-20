package com.bookaholic.backend.model;

import java.util.Date;



public record testeDto(String titulo,String sinopse, Long escritor_id, Long editora_id, Date data_lancamento) {     

}
