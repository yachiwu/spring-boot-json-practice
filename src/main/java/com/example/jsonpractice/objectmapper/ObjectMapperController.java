package com.example.jsonpractice.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectMapperController {
    @GetMapping("/convert")
    public  String convert() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setContactEmail("test@com");
        //user.setName("lucy");
        ObjectMapper objectMapper = new ObjectMapper();
        // User -> json字串
        String jsonResult = objectMapper.writeValueAsString(user);
        System.out.println("json 的值 "+ jsonResult);
        // json字串 -> User
        String json = "{\"id\":3,\"name\":\"judy\",\"age\":30,\"contact_email\":\"test@com\"}";
        User userResult = objectMapper.readValue(json, User.class);
        System.out.println("User的id為 " + userResult.getId());
        System.out.println("User的name為 " + userResult.getName());
        System.out.println("User的email為 " + userResult.getContactEmail());
        return "convert success";
    }
}
