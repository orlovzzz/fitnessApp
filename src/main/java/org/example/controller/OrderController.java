package org.example.controller;

import org.example.entity.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/showProducts")
    public String showProducts(Model model ) {
        orderService.showProducts(model);
        return "redirect:/home";
    }

    @PostMapping("/addToOrder")
    public String addToOrder(@RequestParam("product") String product, HttpSession session, Model model) {
        orderService.addToOrder((String) session.getAttribute("username"), Long.parseLong(product), session, model);
        return "redirect:/home";
    }

    @GetMapping("/order")
    public String getOrder(Model model) {
        Order order = orderService.findLastOrder();
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/buyOrder")
    public String buyOrder(Model model, HttpSession session) {
        orderService.buyOrder(model, session);
        return getOrder(model);
    }

    @GetMapping("/ordersHistory")
    public String getOrdersHistory(Model model, HttpSession session) {
        orderService.getAllOrders(model, (String) session.getAttribute("username"));
        return "ordersHistory";
    }
}
