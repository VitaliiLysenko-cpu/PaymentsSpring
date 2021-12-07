package com.lysenko.Payments.controller;


import com.lysenko.Payments.model.entity.account.Account;
import com.lysenko.Payments.model.entity.account.Status;
import com.lysenko.Payments.model.entity.request.RequestUnblock;
import com.lysenko.Payments.model.entity.request.StatusRequest;
import com.lysenko.Payments.model.entity.user.Role;
import com.lysenko.Payments.model.entity.user.User;
import com.lysenko.Payments.model.entity.user.UserStatus;
import com.lysenko.Payments.model.repository.AccountRepository;
import com.lysenko.Payments.model.repository.RequestUnblockRepository;
import com.lysenko.Payments.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(com.lysenko.Payments.controller.UserController.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RequestUnblockRepository requestUnblockRepository;

    @GetMapping("/admin")
    public String getUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) auth.getPrincipal();
        final List<User> users = userRepository.findAllByRole(Role.USER);
        model.addAttribute("users", users);
        log.debug("Return admin.jsp");
        return "admin";
    }

    @GetMapping("/customer")
    public String getCustomerPage(Model model, @RequestParam("id") int id) {
        log.debug("try to get users accounts");
        final List<Account> accounts = accountRepository.findAllByUserId(id);
        log.debug("accounts: ", accounts);
        model.addAttribute("accounts", accounts);
        model.addAttribute("customerId", id);
        log.debug("model: ", model);
        return "customer";
    }

    @GetMapping("/customer/block")
    public String toBlockCustomer(@RequestParam("userId") int id) {
        User user = userRepository.findUserByUserId(id);
        user.setStatus(UserStatus.BLOCKED);
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/customer/unblock")
    public String toUnblockCustomer(@RequestParam("userId") int id) {
        User user = userRepository.findUserByUserId(id);
        user.setStatus(UserStatus.UNBLOCKED);
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("customer-account/unblock")
    public String toUnblockAccount(@RequestParam("accountId") int accountId, @RequestParam("customerId") int customerId) {
        Account account = accountRepository.findAccountById(accountId);
        account.setStatus(Status.OPEN);
        accountRepository.save(account);
        return "redirect:/customer?id=" + customerId;
    }


    @GetMapping("customer-account/block")
    public String toBlockAccount(@RequestParam("accountId") int accountId, @RequestParam("customerId") int customerId) {
        Account account = accountRepository.findAccountById(accountId);
        account.setStatus(Status.BLOCKED);
        accountRepository.save(account);
        return "redirect:/customer?id=" + customerId;
    }

    @GetMapping("unblock_account")
    public String unblockAccountPage(Model model) {
        List<RequestUnblock> requests = requestUnblockRepository.findRequestUnblockByStatusRequest(StatusRequest.NEW);
        model.addAttribute("requests", requests);
        return "unblock_account";
    }

    @GetMapping("/request_unblock_account")
    public String toUnblockRequestAccount(@RequestParam("id") int accountId) {
        Account account = accountRepository.findAccountById(accountId);
        List<RequestUnblock> requestUnblocks = requestUnblockRepository.findRequestUnblockByAccountId(accountId);
        for (RequestUnblock requestUnblock : requestUnblocks) {
            requestUnblock.setStatusRequest(StatusRequest.DONE);
            requestUnblockRepository.save(requestUnblock);
        }
        account.setStatus(Status.OPEN);

        accountRepository.save(account);
        return "redirect:/unblock_account";
    }
}

