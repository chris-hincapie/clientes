package com.gml.prueba.clientes.infrastructure.output.jpa.repository;

import com.gml.prueba.clientes.infrastructure.output.jpa.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByIdClient(Long idClient);
    @Query("SELECT ce FROM ClientEntity ce  WHERE ce.sharedKey = ?1")
    Optional<ClientEntity> findBySharedKey(String sharedKey);

}
