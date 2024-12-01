package com.poc.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poc.microservice.model.UnnaxCatalog;

import java.util.List;

@Repository
public interface UnnaxCatalogRepository extends JpaRepository<UnnaxCatalog, Integer> {
    UnnaxCatalog findByTraceIdentifier(String traceIdentifier);

    void deleteByTraceIdentifier(String traceIdentifier);

    List<UnnaxCatalog> findAllByTraceIdentifier(String traceIdentifier);
}
