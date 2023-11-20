package com.bookaholic.backend.model;

import java.util.Date;
import java.util.UUID;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "confirmationtoken")
@Table(name  = "confirmationtoken")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long tokenId; 

    @Column(name="confirmation_token") 
    private String confirmationToken; 

    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdDate; 

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario usuario;

    public ConfirmationToken() {}

    public ConfirmationToken(Usuario user) {
        this.usuario = user;
        this.createdDate =  new Date();
        this.confirmationToken = UUID.randomUUID().toString();
    }

    
}
