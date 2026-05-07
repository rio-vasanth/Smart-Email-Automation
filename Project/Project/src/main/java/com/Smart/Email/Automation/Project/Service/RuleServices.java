package com.Smart.Email.Automation.Project.Service;

import com.Smart.Email.Automation.Project.Model.Email;
import com.Smart.Email.Automation.Project.Repository.EmailRepository;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service

public class RuleServices {

    @Autowired
    private Gmail gmail; // This is now created by the Config class above

    public void fetchEmails() throws IOException {
        // This will now work without errors!
        ListMessagesResponse response = gmail.users().messages().list("me")
                .setQ("is:unread")
                .execute();

        System.out.println("Found " + response.getMessages().size() + " new emails.");
    }

            @Autowired

            private EmailRepository emailRepository;

            public Email saveRule(Email email) {

                System.out.println("Service called");

                // Combine fields from your entity
                String text = (email.getName() + " " + email.getKeywords()) + " " +email.getSender().toLowerCase();

                // 🔥 RULE LOGIC (based on keywords)
                if (text.contains("invoice") || text.contains("payment") || text.contains("attend"))  {
                    email.setLabel("finance");
                    email.setPriority(1);
                } else if (text.contains("meeting") || text.contains("schedule")) {
                    email.setLabel("work");
                    email.setPriority(8);
                } else if (text.contains("unsubscribe") || text.contains("offer")) {
                    email.setLabel("spam");
                    email.setPriority(9);
                } else {
                    email.setLabel("general");
                    email.setPriority(5);
                }
                Email savedEmail = emailRepository.save(email);

                // 2. Trigger mobile notification
                sendNotification(savedEmail.getName(), savedEmail.getPriority());

                return emailRepository.save(email);
            }


            public List<Email> getAllRules() {
                return emailRepository.findAll();
            }
    private void sendNotification(String subject, int priority) {
        // Only send notification for High Priority (1 and 8 in your code)
        if (priority == 1 || priority == 8) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                // Use a unique topic name like 'my-private-alerts-123'
                String url = "https://ntfy.sh/Email-automation";

                String message = "Priority Email Alert: " + subject;

                restTemplate.postForObject(url, message, String.class);
                System.out.println("Mobile notification sent!");


            } catch (Exception e) {
                System.err.println("Failed to send notification: " + e.getMessage());
            }
        }
    }



    }

