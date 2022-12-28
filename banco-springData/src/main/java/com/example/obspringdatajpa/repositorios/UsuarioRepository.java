package com.example.obspringdatajpa.repositorios;


import com.example.obspringdatajpa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
