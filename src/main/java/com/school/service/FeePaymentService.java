package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.FeePayment;
import com.school.repository.FeePaymentRepository;

@Service
public class FeePaymentService {

    @Autowired
    private FeePaymentRepository feePaymentRepository;

    // Save fee payment
    public void savePayment(FeePayment payment) {
        feePaymentRepository.save(payment);
    }

    // Get all fee payments
    public List<FeePayment> getAllPayments() {
        return feePaymentRepository.findAll();
    }

    // Count fee payments (for dashboard)
    public long getFeeCount() {
        return feePaymentRepository.count();
    }

    // Calculate total fees collected
    public double getTotalFees() {

        double total = 0;

        List<FeePayment> payments = feePaymentRepository.findAll();

        for (FeePayment fee : payments) {
            total += fee.getAmount();
        }

        return total;
    }
}