package com.bookaholic.backend.model.enums;

import lombok.NoArgsConstructor;


public enum Status {
    
    em_andamento("em andamento"),
    sem_acesso("sem acesso"),
    finalizado("finalizado");

    Status(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus(Status status) {
        return this.status;
    }
}
