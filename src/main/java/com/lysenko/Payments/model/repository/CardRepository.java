package com.lysenko.Payments.model.repository;

import com.lysenko.Payments.model.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findCardByAccountId(Integer accountId);
}
