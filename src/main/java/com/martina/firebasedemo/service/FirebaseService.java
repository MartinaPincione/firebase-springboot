package com.martina.firebasedemo.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    private static final String FIRESTORE_API_URL =
            "https://firestore.googleapis.com/v1/projects/springboot-firebase-testing/databases/firestore-springboot/documents/employee/QTzXS0eCNz81f3jJyp9R";


    public String createEmployee(Map<String, Object> employeeData) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();

        // Add a document to the "employee" collection
        ApiFuture<DocumentReference> future = db.collection("employee").add(employeeData);

        // Wait for the operation to complete and get the document ID
        return "Document created with ID: " + future.get().getId();
    }
    public String getEmployeeDocument(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        // Set up headers with Authorization Bearer token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the GET request to Firestore API
        ResponseEntity<String> response = restTemplate.exchange(
                FIRESTORE_API_URL,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}
