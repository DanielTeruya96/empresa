package com.daniel.empresa.repository;

import com.daniel.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

    Empresa findByCnpj(String cnpj);
}
