package org.example.controller;

import org.example.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    OrderController orderController;

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        Date sub = personRepository.findSubscriptionByUsername(username);
        String coach = personRepository.findCoachByUsername(username);
        if (sub == null) {
            model.addAttribute("subscription", "Subscription is not valid");
        } else {
            model.addAttribute("subscription", "Subscription is valid until " + sub);
        }
        if (coach == null) {
            model.addAttribute("coach", "No coach");
        } else {
            model.addAttribute("coach", coach);
        }
        model.addAttribute("balance", personRepository.findBalanceByUsername(username));
        orderController.showProducts(model);
        return "index";
    }
}
