package com.example.projectshop;

import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    //    private final ItemRepository ItemRepository;
    private final ItemService ItemService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler() {
        return ResponseEntity.status(404).body("not found");
    }

    @GetMapping("/list")
    String list(Model model) {
//        List<item> result = ItemRepository.findAll();
        List<item> result = ItemService.itemFindAll();
        model.addAttribute("item", result);

        return "itemList.html";
    }

    @GetMapping("/write")
    String write() {
        return "writeItem.html";
    }

    @PostMapping("/add")
    String writePost(String title, Integer price) {
        ItemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {
        Optional<item> result = ItemService.itemFindById(id);
        if (result.isPresent()) {
            item item = result.get();
            System.out.println(result.get());
            model.addAttribute("item", item);
            return "detail.html";
        } else {
            return "error";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model) {
        Optional<item> result = ItemService.itemFindById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(String title, Integer price, Long id) {
        item item = new item();
        ItemService.saveItem(title,price,id);
        return "redirect:/list";
    }

    @DeleteMapping("/delete/{id}")
    void deleteItem(@PathVariable Long id) {
        ItemService.deleteItem(id);
//        return "redirect:/list";
    }
}