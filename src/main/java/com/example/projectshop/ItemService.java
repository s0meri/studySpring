package com.example.projectshop;

import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    public void saveItem(String title, Integer price){
        item item = new item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
    public List<item> itemFindAll(){
        return itemRepository.findAll();
    }
    public Optional<item> itemFindById(Long id1){
        return itemRepository.findById(id1);
    }
    public void saveItem(String title, Integer price, Long id){
        item item = new item();
        item.setTitle(title);
        item.setPrice(price);
        item.setId(id);
        itemRepository.save(item);
    }
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
