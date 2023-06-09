package org.example.service;

import org.example.entity.Order;
import org.example.entity.Product;
import org.example.repo.OrderRepository;
import org.example.repo.PersonRepository;
import org.example.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PersonRepository personRepository;

    public void showProducts(Model model) {
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            model.addAttribute("product" + (i + 1), products.get(i).getProduct());
            model.addAttribute("description" + (i + 1), products.get(i).getDescription());
            model.addAttribute("price" + (i + 1), products.get(i).getPrice());
        }
    }

    public void addToOrder(String username, Long product_id, HttpSession session, Model model) {
        Product product = productRepository.findById(product_id).get();
        Order nowOrder = findLastOrder();
        if (nowOrder == null) {
            nowOrder = new Order();
            nowOrder.setCustomerName(username);
            product.setOrder(nowOrder);
            nowOrder.setProducts(Arrays.asList(productRepository.findById(product_id).get()));
        } else {
            product.setOrder(orderRepository.getById(nowOrder.getId()));
            nowOrder.getProducts().add(productRepository.findById(product_id).get());
        }
        orderRepository.save(nowOrder);
    }

    public void buyOrder(Model model, HttpSession session) {
        Order order = findLastOrder();
        int orderSum = 0;
        for (Product product : order.getProducts()) {
            orderSum += product.getPrice();
        }
        if (orderSum > personRepository.findBalanceByUsername((String) session.getAttribute("username"))) {
            model.addAttribute("order_error", "Insufficient funds. Top up your balance.");
            return;
        }
        order.setReady(true);
        orderRepository.save(order);
        model.addAttribute("order", null);
        personRepository.updateBalanceByUsername((String) session.getAttribute("username"), -orderSum);
    }

    public void getAllOrders(Model model, String username) {
        List<Order> orders = orderRepository.findAll();
        List<Order> personOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerName().equals(username)) {
                personOrders.add(order);
            }
        }
        model.addAttribute("orders", personOrders);
    }

    public Order findLastOrder() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (order.isReady() == false) {
                return order;
            }
        }
        return null;
    }
}
