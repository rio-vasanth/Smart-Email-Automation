package com.Smart.Email.Automation.Project.Controller;



import com.Smart.Email.Automation.Project.Model.Email;
import com.Smart.Email.Automation.Project.Service.RuleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class RuleController {




        @Autowired
        private RuleServices ruleService;

        @PostMapping
        public Email createRule(@RequestBody Email email) {
            return ruleService.saveRule( email);
        }

        @GetMapping("/rules")
        public List<Email> getAllRules() {
            return ruleService.getAllRules();
        }


    }
