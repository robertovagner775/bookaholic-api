package com.bookaholic.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookaholic.backend.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

    Privilege findByName(String name);
} 