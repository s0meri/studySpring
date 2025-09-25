package com.example.projectshop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class AddAge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    public void setChangeAge(int age) {
        if (age < 0 || age > 100 )this.age = age;
    }
    public void setAddAge(int age) {
        if (age < 0 || age > 100 )this.age = age + 1;
    }
}
