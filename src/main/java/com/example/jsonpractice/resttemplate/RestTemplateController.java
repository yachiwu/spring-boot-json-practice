package com.example.jsonpractice.resttemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class RestTemplateController {

/*
    {
        "id": 123,
        "name": "lucy",
        "contact_phone": "123456"
    }
 */
    @GetMapping("/getForObject")
    public String getForObject(){
        RestTemplate restTemplate = new RestTemplate();
        Student student = restTemplate.getForObject("https://mocki.io/v1/aadba96e-8234-42f7-a5ea-72765fc40cea",
                Student.class);
        System.out.println("student id 值 " + student.getId());
        System.out.println("student name 值 " + student.getName());
        System.out.println("student phone 值 " + student.getContactPhone());
        return "getForObject success";

    }

/*
    {
        "value": [
            {
                "id": 123,
                "name": "lucy",
                "contact_phone": "123456"
            },
            {
                "id": 456,
                "name": "judy",
                "contact_phone": "999"
            }
        ]
    }
*/
    @GetMapping("/getForEntity")
    public String getForEntity(){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://mocki.io/v1/00130843-1b5d-4119-8c31-20e6443e5eb5";
        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(apiUrl, ApiResponse.class);
        Optional<ApiResponse> optionalResponse = Optional.ofNullable(responseEntity.getBody());
        optionalResponse.ifPresent(responseBody -> {
            List<Student> students = responseBody.getValue();
            for (Student student : students) {
                System.out.println("student id 值 " + student.getId());
                System.out.println("student name 值 " + student.getName());
                System.out.println("student phone 值 " + student.getContactPhone());

            }
        });
        return "getForEntity success";
    }
}
