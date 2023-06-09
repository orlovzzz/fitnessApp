package org.example.controller;

import org.example.entity.Order;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private HomeController homeController;

    @Autowired
    private AccountService accountService;

    @PostMapping("/cashIn")
    public String addBalance(@RequestParam("balance") String balanceStr, HttpSession session) {
        String username = (String) session.getAttribute("username");
        accountService.addBalance(username, balanceStr);
        return "redirect:/home";
    }

    @GetMapping("/oneMouth")
    public String addOneMouth(HttpSession session, Model model) {
        if (accountService.addOneMouth((String) session.getAttribute("username")) == false) {
            return returnHome(session, model, 1);
        }
        return "redirect:/home";
    }

    @GetMapping("/threeMouth")
    public String addThreeMouth(HttpSession session, Model model) {
        if (accountService.addThreeMouth((String) session.getAttribute("username")) == false) {
            return returnHome(session, model, 2);
        }
        return "redirect:/home";
    }

    @GetMapping("/sixMouth")
    public String addSixMouth(HttpSession session, Model model) {
        if (accountService.addSixMouth((String) session.getAttribute("username")) == false) {
            return returnHome(session, model, 3);
        }
        return "redirect:/home";
    }

    @GetMapping("/oneYear")
    public String addYear(HttpSession session, Model model) {
        if (accountService.addYear((String) session.getAttribute("username")) == false) {
            return returnHome(session, model, 4);
        }
        return "redirect:/home";
    }

    @PostMapping("/selectCoach")
    public String selectCoach(@RequestParam("coach") String coach, HttpSession session, Model model) {
        if (accountService.selectCoach((String) session.getAttribute("username"), coach) == false) {
            return returnHome(session, model, 5);
        }
        return "redirect:/home";
    }

    public String returnHome(HttpSession session, Model model, int error) {
        if (error == 5) {
            model.addAttribute("error" + error, "Get a subscription before you choose a coach.");
        } else {
            model.addAttribute("error" + error, "Insufficient funds. Top up your balance.");
        }
        return homeController.homePage(model, session);
    }
}
