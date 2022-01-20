package com.daniel.empresa.repository;

import com.daniel.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

    Empresa findByCnpjAndSituacao(String cnpj, long situacao);

    List<Empresa> findBySituacao(long situacao);
}
