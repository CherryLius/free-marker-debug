package com.example.entity;

import java.util.List;

/**
 * @author cherry
 */
public class Person {
    private Long id;
    private String name;
    private int age;
    private List<String> hobby;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }
    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public List<String> getHobby(){
        return this.hobby;
    }
}