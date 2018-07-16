package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    SandwichRepository sandwichRepository;

    @RequestMapping("/")
    public String listCourses(Model model) {
        model.addAttribute("sandwiches", sandwichRepository.findAll());
        return "list";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/add")
    public String courseForm(Model model) {
        model.addAttribute("sandwich", new Sandwich());
        return "sandwichform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Sandwich sandwich, BindingResult result) {
        if (result.hasErrors()) {
            return "sandwichform";
        }
        sandwichRepository.save(sandwich);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("sandwich", sandwichRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("sandwich", sandwichRepository.findById(id));
        return "sandwichform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id) {
        sandwichRepository.deleteById(id);
        return "redirect:/";
    }
}

