package com.example.wsmeverylisten.entity;

import lombok.Data;

@Data
public class CreateUserAPI {
    private String name;
    private String identity;
    private String mobile;
    private int age;

}
