package com.example.projectshop;

import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository ItemRepository;

    @GetMapping("/get")
    String list(Model model){
        List<item> result = ItemRepository.findAll();
        model.addAttribute("item", result);

        return "itemList.html";
    }

    @GetMapping("/write")
    String write() {
        return "writeItem.html";
    }

    @PostMapping("/add")
    String writePost(@RequestParam Map<String, Object> formData) {
        item item = new item();
        item.setPrice(Integer.parseInt((String) formData.get("price")));
        item.setTitle((String) formData.get("title"));
        ItemRepository.save(item);
        return "redirect:/get";
    }
}