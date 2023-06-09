package org.example.controller;

import org.example.entity.Person;
import org.example.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isEmpty() || !optionalPerson.get().getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
        Person person = optionalPerson.get();
        session.setAttribute("username", person.getUsername());
        return "redirect:/home";
    }
}