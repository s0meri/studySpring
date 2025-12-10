package com.example.projectshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<item, Long> {
    List<item> findAllByTitleContains(String title);

    @Query(value = "SELECT * FROM item WHERE MATCH(title) AGAINST(:text IN BOOLEAN MODE)", nativeQuery = true)
    List<item> searchByTitleFullText(@Param("text") String text);
}
