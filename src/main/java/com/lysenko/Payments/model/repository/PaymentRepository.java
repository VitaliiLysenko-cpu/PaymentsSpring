package com.lysenko.Payments.model.repository;

import com.lysenko.Payments.model.entity.payment.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Page<Payment> findPaymentsByAccountId(Integer accountId, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO payment (amount,account_id,date) VALUES (?,?,?)", nativeQuery = true)
    void createNewPayment(double amount, int account_id, Date date);
}

