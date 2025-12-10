package com.example.projectshop;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler() {
        return ResponseEntity.status(404).body("not found");
    }

    @GetMapping("/list")
    String list(Model model) {
        Page<item> page = itemService.itemFindByPage(0, 4);
        model.addAttribute("number", page.getTotalPages());
        model.addAttribute("item", page.getContent());
        return "itemList.html";
    }

    @GetMapping("/write")
    String write() {
        return "writeItem.html";
    }

    @PostMapping("/add")
    String writePost(String title, Integer price, Authentication auth) {
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        String username = auth.getName();
        itemService.saveItem(title, price, username);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {
        Optional<item> result = itemService.itemFindById(id);
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
        Optional<item> result = itemService.itemFindById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(String title, Integer price, Long id) {
        itemService.saveItem(title, price, id);
        return "redirect:/list";
    }

    @DeleteMapping("/delete/{id}")
    void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
//        return "redirect:/list";
    }

    @GetMapping("/list/page/{cursor}")
    String getListPage(@PathVariable Integer cursor, Model model) {
        Page<item> result = itemService.itemFindByPage(cursor - 1, 4);
        model.addAttribute("number", result.getTotalPages());
        model.addAttribute("item", result.getContent());
        return "itemList.html";
    }

    @GetMapping("/search")
    String searchPage() {
        return "search.html";
    }

    @PostMapping("/search")
    String search(@RequestParam(required = false, defaultValue = "") String searchText, Model model) {
        List<item> result = itemService.itemFindAllByColumn(searchText);
        model.addAttribute("item", result);
        model.addAttribute("searchText", searchText);
        return "search.html";
    }
}
