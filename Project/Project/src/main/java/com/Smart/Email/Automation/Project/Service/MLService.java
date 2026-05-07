package com.Smart.Email.Automation.Project.Service;


import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MLService {


        private final RestTemplate restTemplate = new RestTemplate();

    public String getPrediction(String emailText) {
            try {
                // Prepare the JSON request
                Map<String, String> request = new HashMap<>();
                request.put("text", emailText);

                // Call the Flask API
                String PYTHON_URL = "http://localhost:5000/classify";
                Map response = restTemplate.postForObject(PYTHON_URL, request, Map.class);

                assert response != null;
                return (String) response.get("category");
            } catch (Exception e) {
                // If Python is down, return a default label
                return "General";
            }
        }


}

