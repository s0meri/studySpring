package com.example.projectshop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;
    private Integer price;

    public void setTitle(String title) {
        if(title != null && !title.isEmpty() && title.length() < 256) this.title = title;
    }
    public void setPrice(Integer price) {
        if(price < 0) price = 0;
        this.price = price;
    }
    public void setId(Long id) {
        this.id = id;
    }
}