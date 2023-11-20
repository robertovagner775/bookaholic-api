package com.bookaholic.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class ErrorResponse {

    @Id
    private int statusCode;
    private String type;
    private String message;
    
}
