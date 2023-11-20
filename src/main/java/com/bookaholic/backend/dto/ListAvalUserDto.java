package com.bookaholic.backend.dto;

import java.util.Date;

public record ListAvalUserDto(int qtd_estrela, String nome_usuario, String descricao, Date created) {
    
}
