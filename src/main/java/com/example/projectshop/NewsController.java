package com.example.projectshop;

import com.example.projectshop.news;
import com.example.projectshop.newsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private final newsRepository newsRepository;

//    @GetMapping("/get")
//    String list(Model model){
//        List<news> result = newsRepository.findAll();
//        model.addAttribute("news", result);
//
//        return "list.html";
    }
