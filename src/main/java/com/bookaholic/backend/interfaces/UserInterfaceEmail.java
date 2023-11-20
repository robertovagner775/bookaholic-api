package com.bookaholic.backend.interfaces;

import org.springframework.http.ResponseEntity;

import com.bookaholic.backend.model.Usuario;

public interface UserInterfaceEmail {

    ResponseEntity<?> saveUser(Usuario usuario);

    ResponseEntity<?> confirmEmail(String confirmationToken);
}