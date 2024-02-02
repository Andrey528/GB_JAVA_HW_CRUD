package com.example.Eml4Sem2CRUD.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private int id;
    private String firstName;
    private String lastName;
}
