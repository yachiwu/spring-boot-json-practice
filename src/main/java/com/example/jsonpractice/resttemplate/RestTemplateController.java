package com.example.jsonpractice.resttemplate;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/getForObjectWithParam")
    public String getForObjectWithParam() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        Student student = restTemplate.getForObject(
                "https://mocki.io/v1/77f69435-224e-4c90-b7cd-53f07bbf5604",
                Student.class,
                queryParamMap
        );

        return "getForObject with param success";
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

    @GetMapping("/postForObject")
    public String postForObject() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");

        Student result = restTemplate.postForObject(
                "https://mocki.io/v1/b7a68eb2-f77c-43cf-bffe-8c647e9a2514",
                studentRequestBody,
                Student.class
        );

        return "postForObject success";
    }

    @GetMapping("/postForEntity")
    public String postForEntity() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");

        ResponseEntity<Student> responseEntity = restTemplate.postForEntity(
                "https://mocki.io/v1/b7a68eb2-f77c-43cf-bffe-8c647e9a2514",
                studentRequestBody,
                Student.class
        );

        return "postForEntity success";
    }


    @GetMapping("/exchange")
    public String exchange() {

        RestTemplate restTemplate = new RestTemplate();

        // 使用 exchange 發起 GET 請求
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("header1", "123");

        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        ResponseEntity<Student> getStudentEntity = restTemplate.exchange(
                "https://mocki.io/v1/b7a68eb2-f77c-43cf-bffe-8c647e9a2514",
                HttpMethod.GET,
                requestEntity,
                Student.class,
                queryParamMap
        );



        // 使用 exchange 發起 POST 請求
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.set("header2", "456");
        requestHeaders2.setContentType(MediaType.APPLICATION_JSON);

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");

        HttpEntity requestEntity2 = new HttpEntity(studentRequestBody, requestHeaders2);

        ResponseEntity<Student> postStudentEntity = restTemplate.exchange(
                "https://mocki.io/v1/b7a68eb2-f77c-43cf-bffe-8c647e9a2514",
                HttpMethod.POST,
                requestEntity2,
                Student.class
        );

        return "exchange success";
    }
}
