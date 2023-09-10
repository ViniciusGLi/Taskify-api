package com.taskifi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskifi.api.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
    
}
