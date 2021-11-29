package com.lysenko.Payments.model.repository;

import com.lysenko.Payments.model.entity.account.Account;
import com.lysenko.Payments.model.entity.account.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Page<Account> findAccountsByUserId(int userId, Pageable pageable);
    Account findAccountById(int id);
    List<Account> findAllByUserId(int id);
    List<Account> findAccountByStatus(Status status);
}
