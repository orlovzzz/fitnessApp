package org.example.controller;

import org.example.entity.Person;
import org.example.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/register")
    public String registrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("person") Person person, Model model) {
        if (personRepository.findByUsername(person.getUsername()).isPresent()) {
            model.addAttribute("error", "User already exists");
            return "registration";
        }

        personRepository.save(person);

        return "redirect:/";
    }
}