package com.example.obspringdatajpa.repositorios;


import com.example.obspringdatajpa.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
