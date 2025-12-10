package com.example.projectshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<item, Long> {
    List<item> findAllByTitleContains(String title);
}
