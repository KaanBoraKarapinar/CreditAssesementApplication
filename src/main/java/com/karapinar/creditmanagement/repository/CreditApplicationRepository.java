package com.karapinar.creditmanagement.repository;

import com.karapinar.creditmanagement.model.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication,Long> {



}
