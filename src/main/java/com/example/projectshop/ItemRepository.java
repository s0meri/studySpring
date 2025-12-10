package com.example.projectshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<item, Long> {
    @Query(value = "SELECT * FROM todos.item WHERE MATCH(title) AGAINST(?1)",  nativeQuery = true)
    List<item> rawQuery1(String text);
}
