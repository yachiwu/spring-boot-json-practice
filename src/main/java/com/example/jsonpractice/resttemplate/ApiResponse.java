package com.example.jsonpractice.resttemplate;

import java.util.List;

public class ApiResponse {
    List<Student> value;

    public List<Student> getValue() {
        return value;
    }

    public void setValue(List<Student> value) {
        this.value = value;
    }
}
