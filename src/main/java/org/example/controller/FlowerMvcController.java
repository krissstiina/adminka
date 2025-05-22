package org.example.controller;

import org.example.model.Flower;
import org.example.service.FlowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flowers")
public class FlowerMvcController {
    private final FlowerService service;

    public FlowerMvcController(FlowerService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("flowers", service.getAll().values());
        return "flowers/list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        Flower flower = service.getById(id);
        if (flower != null) {
            model.addAttribute("flower", flower);
            return "flowers/detail";
        }
        return "error";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("flower", new Flower());
        return "flowers/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Flower flower) {
        service.save(flower);
        return "redirect:/flowers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Flower flower = service.getById(id);
        if (flower != null) {
            model.addAttribute("flower", flower);
            return "flowers/edit";
        }
        return "error";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute Flower updated) {
        if (service.getById(id) != null) {
            updated.setId(id);
            service.save(updated);
            return "redirect:/flowers";
        }
        return "error";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "redirect:/flowers";
    }
}