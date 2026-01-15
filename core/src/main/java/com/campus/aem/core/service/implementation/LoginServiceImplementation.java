package com.campus.aem.core.service.implementation;

import com.campus.aem.core.service.LoginService;
import org.osgi.service.component.annotations.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component(service = LoginService.class)
public class LoginServiceImplementation implements LoginService {
    private final Map<String, String> tempMap;

    public LoginServiceImplementation() {
        tempMap = new HashMap<>();
        tempMap.put("admin@gmail.com", "admin123");
        tempMap.put("user1@gmail.com", "password123");
        tempMap.put("user2@gmail.com", "test@123");
        tempMap.put("dhruvsaini0904@gmail.com", "dhruvsaini@123");
        tempMap.put("dhruvsaini04@gmail.com", "dhruvsaini@123");
    }

    @Override
    public boolean authenticate(String email, String password) {
        return tempMap.containsKey(email) && tempMap.get(email).equals(password);
    }
}
