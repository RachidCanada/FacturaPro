package com.example.FacturaPro.repository;

import com.example.FacturaPro.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}
