package com.lysenko.Payments.service;

import com.lysenko.Payments.model.entity.account.Account;
import com.lysenko.Payments.model.entity.account.Status;
import com.lysenko.Payments.model.entity.card.Card;
import com.lysenko.Payments.model.repository.AccountRepository;
import com.lysenko.Payments.model.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserService {
    final AccountRepository accountRepository;
    final CardRepository cardRepository;

    @Autowired
    public UserService(AccountRepository accountRepository, CardRepository cardRepository) {

        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public void createNewAccount(int userId) {
        log.debug("creating new Account");
        Account newAccount = new Account();
        Long number = NumberGenerator.get16DigitsNumber();
        newAccount.setUserId(userId);
        newAccount.setName("name " + number);
        newAccount.setStatus(Status.OPEN);
        newAccount.setNumber(number.toString());
        newAccount.setBalance(0.00);
        log.debug("saving hew account to db");
        final Account account = accountRepository.save(newAccount);
        createNewCard(account.getId());
    }

    private void createNewCard(int id) {
        log.debug("creating new card");
        Date date = new Date();
        Card newCard = new Card();
        Long number = NumberGenerator.get16DigitsNumber();
        int cvc = NumberGenerator.get3DigitsNumber();
        newCard.setNumber(number.toString());
        newCard.setExpiration(date);
        newCard.setCvc(cvc);
        newCard.setAccountId(id);
        log.debug("saving new card to db");
        cardRepository.save(newCard);
    }
}
