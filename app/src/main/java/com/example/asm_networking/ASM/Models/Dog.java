package com.example.asm_networking.ASM.Models;

import java.io.Serializable;

public class Dog implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Integer quantity;
    private Double price;
    private String image;
    private String about;
    private String breed;



    public Dog() {
    }

    public Dog(Integer id, String name, Integer age, Integer quantity, Double price, String image, String about, String breed) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.about = about;
        this.breed = breed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
