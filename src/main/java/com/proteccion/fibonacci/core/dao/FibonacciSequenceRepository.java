package com.proteccion.fibonacci.core.dao;

import com.proteccion.fibonacci.core.models.FibonacciSequence;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface FibonacciSequenceRepository extends JpaRepository<FibonacciSequence, Long> {
}
