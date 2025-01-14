package com.martina.firebasedemo.controller;

import com.martina.firebasedemo.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    private final FirebaseService firestoreService;

    public FirebaseController(FirebaseService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @PostMapping("/create-employee")
    public String createEmployee(@RequestBody Map<String, Object> employeeData) throws ExecutionException, InterruptedException {
        return firestoreService.createEmployee(employeeData);
    }

    @GetMapping("/get-employee")
    public String getEmployee(@RequestParam String accessToken) {
        return firestoreService.getEmployeeDocument(accessToken);
    }
}
