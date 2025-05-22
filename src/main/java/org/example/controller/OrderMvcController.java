package org.example.controller;

import org.example.model.Flower;
import org.example.model.Order;
import org.example.service.FlowerService;
import org.example.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderMvcController {
    private final OrderService orderService;
    private final FlowerService flowerService;

    public OrderMvcController(OrderService orderService, FlowerService flowerService) {
        this.orderService = orderService;
        this.flowerService = flowerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderService.getAll().values());
        return "orders/list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        Order order = orderService.getById(id);
        if (order != null) {
            model.addAttribute("order", order);
            return "orders/detail";
        }
        return "error";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("availableFlowers", flowerService.getAll().values());
        return "orders/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String customerName,
                         @RequestParam String phoneNumber,
                         @RequestParam List<Integer> selectedFlowers) {

        List<Flower> flowers = flowerService.getByIds(selectedFlowers);
        Order order = new Order(customerName, flowers, orderService.getNextId(), phoneNumber);
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Order order = orderService.getById(id);
        if (order != null) {
            model.addAttribute("order", order);
            model.addAttribute("availableFlowers", flowerService.getAll().values());
            model.addAttribute("selectedFlowers", order.getFlowers().stream()
                    .map(Flower::getId)
                    .collect(Collectors.toList()));
            return "orders/edit";
        }
        return "error";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id,
                         @RequestParam String customerName,
                         @RequestParam String phoneNumber,
                         @RequestParam List<Integer> selectedFlowers) {

        Order existing = orderService.getById(id);
        if (existing != null) {
            List<Flower> flowers = flowerService.getByIds(selectedFlowers);
            Order updated = new Order(customerName, flowers, id, phoneNumber);
            orderService.save(updated);
        }
        return "redirect:/orders";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        orderService.delete(id);
        return "redirect:/orders";
    }
}