package com.bookaholic.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookaholic.backend.model.Epub;

public interface EpubRepository extends JpaRepository<Epub, Long>{

    
}
