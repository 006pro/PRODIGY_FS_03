package com.grocery.grocery.controller;


import com.grocery.grocery.model.Product;
import com.grocery.grocery.model.User;
import com.grocery.grocery.repository.ProductRepo;
import com.grocery.grocery.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/sign")
    public String sign(@RequestParam("email") String e,@RequestParam("password") String p){

        User o=userRepo.findByEmail(e);

        if(o==null && !(p.isEmpty()) && !(e.isEmpty())){
            User ob = new User(e,p);
            userRepo.save(ob);//INSERT INTO USER WHERE EMAIL=E,PASSWORD=P

            return "login";
        }
        else{
            return "signup";
        }

    }

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/log")
    public String log(@RequestParam("email") String email, @RequestParam("password") String p,Model model){

        User u = userRepo.findByEmail(email);
        if(u.password.equals(p)){

            List<Product> o=productRepo.findAll();
            model.addAttribute("products",o);
            return "main";
        }
        else {
            return "login";
        }
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }

    @GetMapping("/add_product")
    public String add_product(){
        return "add_product";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute Product p){
        productRepo.save(p);
        return "add_product";

    }

    @GetMapping("/success")
    public String sucess(){
        return "success";
    }


}
