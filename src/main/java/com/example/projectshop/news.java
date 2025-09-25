package com.example.projectshop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class news {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String date;
    private String writer;
}
