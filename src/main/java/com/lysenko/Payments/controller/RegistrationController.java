package com.lysenko.Payments.controller;

import com.lysenko.Payments.model.entity.user.Role;
import com.lysenko.Payments.model.entity.user.User;
import com.lysenko.Payments.model.entity.user.UserStatus;
import com.lysenko.Payments.model.repository.UserRepository;
import com.lysenko.Payments.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String showFormForRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUser(@RequestParam("email") String email, @RequestParam("firstname") String name,
                                      @RequestParam("lastname") String surname, @RequestParam("phone") String phone,
                                      @RequestParam("password") String password) {
        log.debug("creating new user");
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setPhone(phone);
        newUser.setPassword(password);
        newUser.setRole(Role.USER);
        newUser.setStatus(UserStatus.UNBLOCKED);
        log.debug("saving new user to db");
        final User user = userRepository.save(newUser);
        userService.createNewAccount(user.getUserId());
        return "redirect:/";
    }
}
