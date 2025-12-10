package com.example.projectshop;

import com.example.projectshop.mamber.Member;
import com.example.projectshop.mamber.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public void saveItem(String title, Integer price, String username){
        item item = new item();
        item.setTitle(title);
        item.setPrice(price);
        if (username != null) {
            Member member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수 없습니다: " + username));
            item.setAuthor(member);
        }
        itemRepository.save(item);
    }
    public List<item> itemFindAll(){
        return itemRepository.findAll();
    }
    public Optional<item> itemFindById(Long id1){
        return itemRepository.findById(id1);
    }
    public void saveItem(String title, Integer price, Long id){
        item existing = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));
        existing.setTitle(title);
        existing.setPrice(price);
        itemRepository.save(existing);
    }
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
    public Page<item> itemFindByPage(int page, int size) {
        return itemRepository.findAll(PageRequest.of(page, size));
    }
    public void itemFindAllByColumn(String searchText){
        itemRepository.findAllByTitleContains(searchText);
    }
}
