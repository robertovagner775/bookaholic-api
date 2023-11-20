package com.bookaholic.backend.model;

import java.util.Date;

public record LeitorDto(String RG, String CPF, String nome_completo, Date data_nascimento, Long idUser) {
    
}
