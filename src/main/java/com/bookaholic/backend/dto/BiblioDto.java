package com.bookaholic.backend.dto;

public record BiblioDto(Long id, String titulo, String subtitulo, String sinopsem , String pathImagem, String pathEpub, String status) {
}