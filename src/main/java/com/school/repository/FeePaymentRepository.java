package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.model.FeePayment;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {

}