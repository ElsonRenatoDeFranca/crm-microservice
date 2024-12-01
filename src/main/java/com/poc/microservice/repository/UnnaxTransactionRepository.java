package com.poc.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poc.microservice.model.UnnaxTransaction;

import java.util.List;

@Repository
public interface UnnaxTransactionRepository extends JpaRepository<UnnaxTransaction, Integer> {
    UnnaxTransaction findByTraceIdentifier(String traceIdentifier);
    void deleteByTraceIdentifier(String traceIdentifier);
    List<UnnaxTransaction> findAllByTraceIdentifier(String traceIdentifier);
}
