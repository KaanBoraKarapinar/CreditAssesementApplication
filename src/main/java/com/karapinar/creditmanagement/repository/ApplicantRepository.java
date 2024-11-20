package com.karapinar.creditmanagement.repository;

import com.karapinar.creditmanagement.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {

    // Search by FullName
    Optional<Applicant> findByFullname(String fullname);

    // Search by Parts of Name
    List<Applicant> findByFullnameContaining(String partialName);

    // Search by PassportNr
    Optional<Applicant> findByPassportNumber(String PassportNumber);

    boolean existsByPassportNumber(String PassportNumber);

}
